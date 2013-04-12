package org.billingstack;

import java.math.BigDecimal;

public class VolumeTier {

	private BigDecimal from;
	
	private BigDecimal to;
	
	private BigDecimal price;

	/**
	 * @return the from
	 */
	public BigDecimal getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(BigDecimal from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public BigDecimal getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(BigDecimal to) {
		this.to = to;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VolumeTier [from=" + from + ", to=" + to + ", price=" + price
				+ "]";
	}
	
}
