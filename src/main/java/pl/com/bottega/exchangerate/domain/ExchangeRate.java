package pl.com.bottega.exchangerate.domain;

import pl.com.bottega.exchangerate.domain.commands.CreateExchangeRateCommand;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
@IdClass(ExchangeRate.PK.class)
public class ExchangeRate implements Serializable {

	private static final ExchangeRate DEFAULT = new ExchangeRate(null, "PLN", BigDecimal.ONE);

	@Id
	private LocalDate date;

	@Id
	private String currency;

	private BigDecimal rate;

	private ExchangeRate(LocalDate date, String currency, BigDecimal rate) {
		this.date = date;
		this.currency = currency;
		this.rate = rate;
	}

	private ExchangeRate() {
	}

	public static ExchangeRate from(CreateExchangeRateCommand command) {
		LocalDate date = command.getDate();
		String currency = command.getCurrency();
		BigDecimal rate = command.getRate();
		return new ExchangeRate(date, currency, rate);
	}

	public static ExchangeRate getDefault() {
		return DEFAULT;
	}

	public BigDecimal exchangeToBaseCurrency(BigDecimal primaryAmount) {
		return primaryAmount.multiply(rate);
	}

	public BigDecimal exchangeFromBaseCurrency(BigDecimal baseCurrencyAmount) {
		return baseCurrencyAmount.divide(rate, 2, RoundingMode.FLOOR);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ExchangeRate that = (ExchangeRate) o;

		if (date != null ? !date.equals(that.date) : that.date != null) return false;
		return currency != null ? currency.equals(that.currency) : that.currency == null;
	}

	@Override
	public int hashCode() {
		int result = date != null ? date.hashCode() : 0;
		result = 31 * result + (currency != null ? currency.hashCode() : 0);
		return result;
	}

	public void update(CreateExchangeRateCommand command) {
		this.date = command.getDate();
		this.currency = command.getCurrency();
		this.rate = command.getRate();
	}

	public static class PK implements Serializable {

		private LocalDate date;
		private String currency;

		private PK(LocalDate date, String currency) {
			this.date = date;
			this.currency = currency;
		}

		public PK() {
		}

		public static PK from(LocalDate date, String currency) {
			return new PK(date, currency);
		}

	}

}
