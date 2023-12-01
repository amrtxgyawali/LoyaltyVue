package ca.lambton.LoyaltiVue.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import ca.lambton.LoyaltiVue.models.TestTable;

@Repository
public interface TestTableRepository extends MongoRepository<TestTable, String>{

	 @Query("{name:'?0'}")
	  List<TestTable> findTestDataByName(String name);
}
