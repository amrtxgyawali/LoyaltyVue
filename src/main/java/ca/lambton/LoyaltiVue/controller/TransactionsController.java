package ca.lambton.LoyaltiVue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.lambton.LoyaltiVue.models.Transactions;
import ca.lambton.LoyaltiVue.models.TransactionsDTO;
import ca.lambton.LoyaltiVue.service.TransactionsService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

	@Autowired
	private TransactionsService transactionsService;

	@GetMapping
	public ResponseEntity<?> getAllTransactions() {
		try {
			List<TransactionsDTO> transactions = transactionsService.getAllTransactions();
			if (transactions != null)
				return ResponseEntity.ok(transactions);
			else
				throw new Exception("Error gettig transactions");
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getTransactionById(@PathVariable String id) {
		try {
			TransactionsDTO transaction = transactionsService.getTransactionById(id);
			if (transaction != null)
				return ResponseEntity.ok(transaction);
			else
				throw new Exception("Error reading transaction");
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}


	@PutMapping("/{id}")
	public ResponseEntity<?> updateTransaction(@PathVariable String id, @RequestBody TransactionsDTO transactionsDTO) {
		try {
			TransactionsDTO transaction = transactionsService.updateTransaction(id, transactionsDTO);
			if (transaction != null)
				return ResponseEntity.ok(transaction);
			else
				throw new Exception("Error updating exisitng transaction");
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTransaction(@PathVariable String id) {

		try {
			transactionsService.deleteTransaction(id);
			return ResponseEntity.ok("Delete successful.");
		} catch (Exception ex) {
			return new ResponseEntity<String>("Error deleting transaction", HttpStatus.BAD_REQUEST);
		}

	}
	
	@PostMapping
	public ResponseEntity<?> initiateTransaction(@RequestBody TransactionsDTO transactionsDTO) {
		try {
			TransactionsDTO transaction = transactionsService.createTransaction(transactionsDTO);
			if (transaction != null)
				return ResponseEntity.ok(transaction);
			else
				throw new Exception("Error saving transaction");
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
