package pl.com.bottega.exchangerate.api.standard;

import pl.com.bottega.exchangerate.api.ExchangeCalculator;
import pl.com.bottega.exchangerate.api.ExchangeDto;
import pl.com.bottega.exchangerate.api.ExchangeDtoBuilder;
import pl.com.bottega.exchangerate.domain.ExchangeRate;
import pl.com.bottega.exchangerate.domain.ExchangeRateRepository;
import pl.com.bottega.exchangerate.domain.commands.ExchangeQuery;

import java.math.BigDecimal;
import java.time.LocalDate;

public class StandardExchangeCalculator implements ExchangeCalculator {

	private ExchangeRateRepository repository;

	public StandardExchangeCalculator(ExchangeRateRepository repository) {
		this.repository = repository;
	}

	@Override
	public ExchangeDto calculateExchange(ExchangeQuery query) {
		LocalDate date = query.getDate();
		String from = query.getFrom();
		String to = query.getTo();

		ExchangeRate.PK fromKey = ExchangeRate.PK.from(date, from);
		ExchangeRate.PK toKey = ExchangeRate.PK.from(date, to);

		ExchangeRate fromExchangeRate = repository.get(fromKey).orElseGet(ExchangeRate::getDefault);
		ExchangeRate toExchangeRate = repository.get(toKey).orElseGet(ExchangeRate::getDefault);

		BigDecimal primaryAmount = query.getAmount();
		BigDecimal secondaryAmount = calculateSecondaryAmount(fromExchangeRate, toExchangeRate, primaryAmount);

		return prepareExchangeRateDto(from, to, primaryAmount, secondaryAmount);
	}

	private BigDecimal calculateSecondaryAmount(ExchangeRate fromExchangeRate, ExchangeRate toExchangeRate,
												BigDecimal primaryAmount) {
		BigDecimal baseCurrencyAmount = fromExchangeRate.exchangeToBaseCurrency(primaryAmount);
		return toExchangeRate.exchangeFromBaseCurrency(baseCurrencyAmount);
	}

	private ExchangeDto prepareExchangeRateDto(String from, String to, BigDecimal primaryAmount,
											   BigDecimal secondaryAmount) {
		ExchangeDtoBuilder builder = new ExchangeDtoBuilder();
		return builder
				.addFrom(from)
				.addTo(to)
				.addPrimaryAmount(primaryAmount)
				.addSecondaryAmount(secondaryAmount)
				.build();
	}

}
