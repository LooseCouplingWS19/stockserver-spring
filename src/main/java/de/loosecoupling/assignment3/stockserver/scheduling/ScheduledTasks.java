package de.loosecoupling.assignment3.stockserver.scheduling;

import java.util.List;
import java.util.Random;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.loosecoupling.assignment3.stockserver.data.CompanyStock;
import de.loosecoupling.assignment3.stockserver.messaging.MessagePublisher;

@Component
public class ScheduledTasks {

	@Autowired
	List<CompanyStock> stockData;
	@Autowired
	Random random;
	@Autowired
	MessagePublisher messagePublisher;

	/**
	 * Perform first time 10 seconds after service is up and then every 5 seconds.
	 */
	@Scheduled(fixedDelay = 5000, initialDelay = 10000)
	public void updateStockData() {
		for (CompanyStock company : stockData) {
			// 50% chance that one entry of data is changed
			if (random.nextInt(100) + 1 > 50) {
				// Adjust between 1 and 50
				int stockAdjustment = random.nextInt(50) + 1;
				// 0 or 1 for up or down
				int upOrDown = random.nextInt(2);
				// Current company value
				int currentValue = company.getCompanyValue();
				// Lower value
				if (upOrDown == 0) {
					company.setCompanyValue(currentValue - stockAdjustment);
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
