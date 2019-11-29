package de.loosecoupling.assignment3.stockserver.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

	@Bean
	public List<CompanyStock> stockData() {
		List<CompanyStock> data = new ArrayList<CompanyStock>();
		
		data.add(new CompanyStock("Porsche", 50));
		data.add(new CompanyStock("Mercedes", 50));
		data.add(new CompanyStock("Bosch", 50));
		data.add(new CompanyStock("Microsoft", 50));
		data.add(new CompanyStock("Sony", 50));
		data.add(new CompanyStock("Nintendo", 50));
		data.add(new CompanyStock("Bethesda", 50));
		data.add(new CompanyStock("Apple", 50));
		data.add(new CompanyStock("Amazon", 50));
		data.add(new CompanyStock("Facebook", 50));
		data.add(new CompanyStock("Porsche", 50));
		data.add(new CompanyStock("Alphabet", 50));
		data.add(new CompanyStock("Porsche", 50));
		data.add(new CompanyStock("VISA", 50));
		data.add(new CompanyStock("Samsung", 50));
		data.add(new CompanyStock("Huawei", 50));	
		
		return data;
	}
	
	@Bean
	public Random random() {
		return new Random();
	}
}
