package ca.lambton.LoyaltiVue.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ca.lambton.LoyaltiVue.models.TestTable;

@Service
public interface TestService {
	
	public String saveTestData(TestTable testData);

	public List<TestTable> getTestDataByName(String name);

}
