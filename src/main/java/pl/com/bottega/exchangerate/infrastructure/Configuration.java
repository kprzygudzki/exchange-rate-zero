package pl.com.bottega.exchangerate.infrastructure;

import org.springframework.context.annotation.Bean;
import pl.com.bottega.exchangerate.api.ExchangeCalculator;
import pl.com.bottega.exchangerate.api.ExchangeRateRegistry;
import pl.com.bottega.exchangerate.api.standard.StandardExchangeCalculator;
import pl.com.bottega.exchangerate.api.standard.StandardExchangeRateRegistry;
import pl.com.bottega.exchangerate.domain.ExchangeRateRepository;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Bean
	ExchangeCalculator exchangeCalculator(ExchangeRateRepository exchangeRateRepository) {
		return new StandardExchangeCalculator(exchangeRateRepository);
	}

	@Bean
	ExchangeRateRegistry exchangeRateRegistry(ExchangeRateRepository exchangeRateRepository) {
		return new StandardExchangeRateRegistry(exchangeRateRepository);
	}

	@Bean
	ExchangeRateRepository exchangeRateRepository() {
		return new JPAExchangeRateRepository();
	}

}
