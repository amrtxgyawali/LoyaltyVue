package ca.lambton.LoyaltiVue.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import ca.lambton.LoyaltiVue.models.Transactions;

public interface TransactionsRepository extends MongoRepository<Transactions, String> {

	List<Transactions> findByUserPhoneNumber(long userPhoneNumber, Sort sort);
}

