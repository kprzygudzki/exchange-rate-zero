package pl.com.bottega.exchangerate.domain;

import java.time.LocalDate;
import java.util.Optional;

public interface ExchangeRateRepository {

	void put(ExchangeRate exchangeRate);

	Optional<ExchangeRate> get(ExchangeRate.PK primaryKey);
}
