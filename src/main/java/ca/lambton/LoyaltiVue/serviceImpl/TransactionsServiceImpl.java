package ca.lambton.LoyaltiVue.serviceImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ca.lambton.LoyaltiVue.models.Promotions;
import ca.lambton.LoyaltiVue.models.Transactions;
import ca.lambton.LoyaltiVue.models.TransactionsDTO;
import ca.lambton.LoyaltiVue.repository.TransactionsRepository;
import ca.lambton.LoyaltiVue.service.PromotionsService;
import ca.lambton.LoyaltiVue.service.TransactionsService;

@Service
public class TransactionsServiceImpl implements TransactionsService {

	@Autowired
	private TransactionsRepository transactionsRepository;

	@Autowired
	private PromotionsService promotionsService;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<TransactionsDTO> getAllTransactions() {
		List<Transactions> transactionsList = transactionsRepository.findAll();
		return mapTransactionsListToDTOs(transactionsList);
	}

	@Override
	public TransactionsDTO getTransactionById(String id) {
		Optional<Transactions> optionalTransaction = transactionsRepository.findById(id);
		return optionalTransaction.map(transaction -> modelMapper.map(transaction, TransactionsDTO.class)).orElse(null);
	}

	@Override
	public TransactionsDTO createTransaction(TransactionsDTO transactionsDTO) {
		Transactions newTransaction = modelMapper.map(transactionsDTO, Transactions.class);
		List<Transactions> oldTransactions = transactionsRepository.findByUserPhoneNumber(
				transactionsDTO.getUserPhoneNumber(), Sort.by(Sort.Direction.DESC, "transactionDate"));
		Transactions lastTransaction = !oldTransactions.isEmpty() ? oldTransactions.get(0) : new Transactions();
		if (lastTransaction != null) {
			if (transactionsDTO.getRedeemPoints() > 0
					&& transactionsDTO.getRedeemPoints() <= lastTransaction.getPointsTillDate()) {
				newTransaction
						.setPointsTillDate(lastTransaction.getPointsTillDate() - transactionsDTO.getRedeemPoints());
			} else {
				if (transactionsDTO.getRedeemPoints() > 0) {

					newTransaction.setRemarks(
							"Reedem of points: " + transactionsDTO.getRedeemPoints() + " failed. Insufficient points.");
					newTransaction.setRedeemPoints(0);
				}
			}

		}
		Optional<List<Promotions>> activePromotions = promotionsService.getActivePromotion();
		if (activePromotions.isPresent()) {
			List<Promotions> promotions = activePromotions.get();
			promotions.sort(Comparator.comparingDouble(Promotions::getPromoPercentage).reversed());
			Promotions bestPromo = promotions.get(0);
			int pointsEarned = (int) Math
					.ceil((bestPromo.getPromoPercentage() / 100) * transactionsDTO.getTransactionAmount());
			newTransaction.setPointsEarned(lastTransaction.getPointsEarned() + pointsEarned);
			newTransaction.setOfferApplied(bestPromo.getPromoName());

		}
		Transactions savedTransaction = transactionsRepository.save(newTransaction);
		return modelMapper.map(savedTransaction, TransactionsDTO.class);
	}

	@Override
	public TransactionsDTO updateTransaction(String id, TransactionsDTO transactionsDTO) {
		Optional<Transactions> optionalTransaction = transactionsRepository.findById(id);

		if (optionalTransaction.isPresent()) {
			Transactions existingTransaction = optionalTransaction.get();
			// Update the existing transaction with new values
			existingTransaction.setTransactionDate(transactionsDTO.getTransactionDate());
			existingTransaction.setPointsTillDate(transactionsDTO.getPointsTillDate());
			existingTransaction.setOfferApplied(transactionsDTO.getOfferApplied());
			existingTransaction.setPointsEarned(transactionsDTO.getPointsEarned());

			Transactions updatedTransaction = transactionsRepository.save(existingTransaction);
			return modelMapper.map(updatedTransaction, TransactionsDTO.class);
		} else {
			return null; // Handle the case where the transaction with the given ID is not found
		}
	}

	@Override
	public void deleteTransaction(String id) {
		transactionsRepository.deleteById(id);
	}

	// Additional method to convert a list of Transactions to a list of
	// TransactionsDTO
	private List<TransactionsDTO> mapTransactionsListToDTOs(List<Transactions> transactionsList) {
		return transactionsList.stream().map(transaction -> modelMapper.map(transaction, TransactionsDTO.class))
				.collect(Collectors.toList());
	}
}
