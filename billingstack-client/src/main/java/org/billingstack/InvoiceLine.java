package org.billingstack;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

public class InvoiceLine {

	private String id;
	
	@JsonProperty("invoice_id")
	private String invoice;
	
	private String description;
	
	private BigDecimal quantity;
	
	private BigDecimal price;
	
	private BigDecimal subtotal;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the invoice
	 */
	public String getInvoice() {
		return invoice;
	}

	/**
	 * @param invoice the invoice to set
	 */
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the quantity
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
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

	/**
	 * @return the subtotal
	 */
	public BigDecimal getSubtotal() {
		return subtotal;
	}

	/**
	 * @param subtotal the subtotal to set
	 */
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InvoiceLine [id=" + id + ", invoice=" + invoice
				+ ", description=" + description + ", quantity=" + quantity
				+ ", price=" + price + ", subtotal=" + subtotal + "]";
	}
	
}
