package ca.lambton.LoyaltiVue.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("TestTable")
public class TestTable {

	@Id
	private String id;
	
	private String name;
	
	private String address;
}
