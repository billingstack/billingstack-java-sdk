package org.billingstack;

import java.math.BigDecimal;

public class VolumeRangePricing {

	private BigDecimal start;
	
	private BigDecimal end;
	
	private BigDecimal price;

	public BigDecimal getStart() {
		return start;
	}

	public void setStart(BigDecimal start) {
		this.start = start;
	}

	public BigDecimal getEnd() {
		return end;
	}

	public void setEnd(BigDecimal end) {
		this.end = end;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
