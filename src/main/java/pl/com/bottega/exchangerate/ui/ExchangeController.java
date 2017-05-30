package pl.com.bottega.exchangerate.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.exchangerate.api.ExchangeCalculator;
import pl.com.bottega.exchangerate.api.ExchangeDto;
import pl.com.bottega.exchangerate.api.ExchangeRateRegistry;
import pl.com.bottega.exchangerate.domain.commands.CreateExchangeRateCommand;
import pl.com.bottega.exchangerate.domain.commands.ExchangeQuery;

@RestController
public class ExchangeController {

	private ExchangeCalculator calculator;
	private ExchangeRateRegistry registry;

	public ExchangeController(ExchangeCalculator calculator, ExchangeRateRegistry registry) {
		this.calculator = calculator;
		this.registry = registry;
	}

	@GetMapping(path = "/calculation")
	ExchangeDto calculateExchange(ExchangeQuery query) {
		return calculator.calculateExchange(query);
	}

	@PutMapping(path = "/exchange-rate")
	void createExchangeRate(@RequestBody CreateExchangeRateCommand command) {
		registry.createExchangeRate(command);
	}

}
