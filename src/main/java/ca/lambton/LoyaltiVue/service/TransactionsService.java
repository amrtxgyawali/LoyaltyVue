package ca.lambton.LoyaltiVue.service;
 
import java.util.List;

import ca.lambton.LoyaltiVue.models.TransactionsDTO;

public interface TransactionsService {

    List<TransactionsDTO> getAllTransactions();

    TransactionsDTO getTransactionById(String id);

    TransactionsDTO createTransaction(TransactionsDTO transactionsDTO);

    TransactionsDTO updateTransaction(String id, TransactionsDTO transactionsDTO);

    void deleteTransaction(String id);
}
