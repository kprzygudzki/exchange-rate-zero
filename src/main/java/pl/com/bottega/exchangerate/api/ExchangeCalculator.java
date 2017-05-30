package pl.com.bottega.exchangerate.api;

import pl.com.bottega.exchangerate.domain.commands.ExchangeQuery;

public interface ExchangeCalculator {
	ExchangeDto calculateExchange(ExchangeQuery query);
}
