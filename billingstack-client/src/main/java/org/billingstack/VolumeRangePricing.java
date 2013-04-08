package org.billingstack;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("volume")
public class VolumeRangePricing {

	private BigDecimal start;
	
	private BigDecimal end;
	
	private BigDecimal price;

	/**
	 * @return the start
	 */
	public BigDecimal getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(BigDecimal start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public BigDecimal getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(BigDecimal end) {
		this.end = end;
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
	
}
