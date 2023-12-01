package ca.lambton.LoyaltiVue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.lambton.LoyaltiVue.models.TestTable;
import ca.lambton.LoyaltiVue.service.TestService;

@RestController
@RequestMapping("/api/test-data")
public class TestController {
	
	@Autowired
	private TestService testService;

	@PostMapping
	public ResponseEntity<?> saveTestData(@RequestBody TestTable testData){
		String response = testService.saveTestData(testData);
		if(response.equalsIgnoreCase("Success")){
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.ok(response);
		}
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<?> getTestDataByName(@PathVariable("name") String name){
		List<TestTable> testData = testService.getTestDataByName(name);
		return ResponseEntity.ok(testData);
	}
	
}
