package pl.com.bottega.exchangerate.api;

import java.math.BigDecimal;

public class ExchangeDtoBuilder {

	private ExchangeDto dto = new ExchangeDto();

	public ExchangeDtoBuilder addFrom(String from) {
		dto.setFrom(from);
		return this;
	}

	public ExchangeDtoBuilder addTo(String to) {
		dto.setFrom(to);
		return this;
	}

	public ExchangeDtoBuilder addPrimaryAmount(BigDecimal primaryAmount) {
		dto.setAmount(primaryAmount);
		return this;
	}

	public ExchangeDtoBuilder addSecondaryAmount(BigDecimal secondaryAmount) {
		dto.setCalculatedAmount(secondaryAmount);
		return this;
	}

	public ExchangeDto build() {
		return dto;
	}

}
