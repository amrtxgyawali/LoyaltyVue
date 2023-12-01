package ca.lambton.LoyaltiVue.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.lambton.LoyaltiVue.models.TestTable;
import ca.lambton.LoyaltiVue.repository.TestTableRepository;
import ca.lambton.LoyaltiVue.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestTableRepository testRepo;
	
	@Override
	public String saveTestData(TestTable testData) {
		String response = "Success";
		try {
		testRepo.save(testData);
		} catch(Exception ex) {
			response = "Failed";
		}
		return response;
	}

	@Override
	public List<TestTable> getTestDataByName(String name) {
		return testRepo.findTestDataByName(name);
	}

}
