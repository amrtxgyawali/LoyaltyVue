package ca.lambton.LoyaltiVue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class LoyaltiVueApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoyaltiVueApplication.class, args);
	}

}
