package pl.com.bottega.exchangerate.api.standard;

import pl.com.bottega.exchangerate.api.ExchangeRateRegistry;
import pl.com.bottega.exchangerate.domain.ExchangeRate;
import pl.com.bottega.exchangerate.domain.ExchangeRateRepository;
import pl.com.bottega.exchangerate.domain.commands.CreateExchangeRateCommand;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public class StandardExchangeRateRegistry implements ExchangeRateRegistry {

	private ExchangeRateRepository repository;

	public StandardExchangeRateRegistry(ExchangeRateRepository repository) {
		this.repository = repository;
	}

	@Override
	public void createExchangeRate(CreateExchangeRateCommand command) {
		Optional<ExchangeRate> optional = repository.get(ExchangeRate.PK.from(command.getDate(), command.getCurrency()));
		if (optional.isPresent()) {
			optional.get().update(command);
		} else {
			ExchangeRate exchangeRate = ExchangeRate.from(command);
			repository.put(exchangeRate);
		}
	}

}
