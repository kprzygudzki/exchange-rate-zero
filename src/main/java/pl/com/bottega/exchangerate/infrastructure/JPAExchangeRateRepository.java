package pl.com.bottega.exchangerate.infrastructure;

import pl.com.bottega.exchangerate.domain.ExchangeRate;
import pl.com.bottega.exchangerate.domain.ExchangeRateRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Optional;

public class JPAExchangeRateRepository implements ExchangeRateRepository {

	@PersistenceContext
	private	EntityManager entityManager;

	@Override
	public void put(ExchangeRate exchangeRate) {
		entityManager.persist(exchangeRate);
	}

	@Override
	public Optional<ExchangeRate> get(ExchangeRate.PK primaryKey) {
		ExchangeRate exchangeRate = entityManager.find(ExchangeRate.class, primaryKey);
		return Optional.ofNullable(exchangeRate);
	}

}
