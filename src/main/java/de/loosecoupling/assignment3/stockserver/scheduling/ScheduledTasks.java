package de.loosecoupling.assignment3.stockserver.scheduling;

import java.util.List;
import java.util.Random;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.loosecoupling.assignment3.stockserver.data.CompanyStock;
import de.loosecoupling.assignment3.stockserver.data.DataInitializer;
import de.loosecoupling.assignment3.stockserver.messaging.MessagePublisher;

@Component
public class ScheduledTasks {

	@Autowired
	Random random;
	@Autowired
	MessagePublisher messagePublisher;
	
	List<CompanyStock> stockData = DataInitializer.stockData();

	/**
	 * Perform first time 10 seconds after service is up and then every 5 seconds.
	 */
	@Scheduled(fixedDelay = 5000, initialDelay = 10000)
	public void updateStockData() {
		for (CompanyStock company : stockData) {
			// 50% chance that one entry of data is changed
			if (random.nextInt(100) + 1 > 50) {
				// Adjust between 1 and 15
				int stockAdjustment = random.nextInt(15) + 1;
				// 0 or 1 for up or down
				int upOrDown = random.nextInt(2);
				// Current company value
				int currentValue = company.getCompanyValue();
				// Lower value
				if (upOrDown == 0) {
					// Company can't be worth less then 1 million euro
					if (currentValue - stockAdjustment < 1) {
						company.setCompanyValue(currentValue - stockAdjustment);
					} else {
						company.setCompanyValue(1);
					}
					
				} else {
					company.setCompanyValue(currentValue + stockAdjustment);
				}
			}
		}
	}

	/**
	 * Perform first time 30 seconds after service is up and then every 10 seconds.
	 * @throws JMSException 
	 */
	@Scheduled(fixedDelay = 10000, initialDelay = 30000)
	public void publishStockData() throws JMSException {
        int chosenCompanyStock = random.nextInt(stockData.size());
        messagePublisher.publishMessageStockMarket(stockData.get(chosenCompanyStock));
	}
}
