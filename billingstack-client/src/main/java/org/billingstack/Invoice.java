package org.billingstack;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Invoice {

	private String id;
	
	@JsonProperty("customer_id")
	private String merchant;
	
	@JsonProperty("customer_id")
	private String customer;
	
	private String identifier;
	
	private Date due;
	
	private BigDecimal subtotal;
	
	@JsonProperty("tax_percentage")
	private BigDecimal taxPercentage;
	
	@JsonProperty("tax_total")
	private BigDecimal taxTotal;
	
	private BigDecimal total;
	
	private String state;

	private String currency;
	
	@JsonProperty("transaction_id")
	private String transaction;
	
	private List<InvoiceLine> lines;

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
	 * @return the merchant
	 */
	public String getMerchant() {
		return merchant;
	}

	/**
	 * @param merchant the merchant to set
	 */
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the due
	 */
	public Date getDue() {
		return due;
	}

	/**
	 * @param due the due to set
	 */
	public void setDue(Date due) {
		this.due = due;
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

	/**
	 * @return the taxPercentage
	 */
	public BigDecimal getTaxPercentage() {
		return taxPercentage;
	}

	/**
	 * @param taxPercentage the taxPercentage to set
	 */
	public void setTaxPercentage(BigDecimal taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	/**
	 * @return the taxTotal
	 */
	public BigDecimal getTaxTotal() {
		return taxTotal;
	}

	/**
	 * @param taxTotal the taxTotal to set
	 */
	public void setTaxTotal(BigDecimal taxTotal) {
		this.taxTotal = taxTotal;
	}

	/**
	 * @return the total
	 */
	public BigDecimal getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the transaction
	 */
	public String getTransaction() {
		return transaction;
	}

	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	/**
	 * @return the lines
	 */
	public List<InvoiceLine> getLines() {
		return lines;
	}

	/**
	 * @param lines the lines to set
	 */
	public void setLines(List<InvoiceLine> lines) {
		this.lines = lines;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", merchant=" + merchant + ", customer="
				+ customer + ", identifier=" + identifier + ", due=" + due
				+ ", subtotal=" + subtotal + ", taxPercentage=" + taxPercentage
				+ ", taxTotal=" + taxTotal + ", total=" + total + ", state="
				+ state + ", currency=" + currency + ", transaction="
				+ transaction + ", lines=" + lines + "]";
	}
	
}
