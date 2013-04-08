package org.billingstack;

import java.util.logging.Logger;

import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.filter.LoggingFilter;

public class BillingStackEndpoint {
	
	private static final LoggingFilter LOGGER = new LoggingFilter(Logger.getLogger("billingstack"), 10000);
	
	private WebTarget target;

	public BillingStackEndpoint(WebTarget endpoint) {
		this.target = endpoint;
	}
	
	public void logger() {
		target.register(LOGGER);
	}
	
	public RolesTarget roles() {
		return new RolesTarget(target);
	}
	
	public RoleTarget role(String roleId) {
		return new RoleTarget(target, roleId);
	}
	
	public InvoiceStatesTarget invoiceStates() {
		return new InvoiceStatesTarget(target);
	}
	
	public InvoiceStateTarget invoiceState(String invoiceStateId) {
		return new InvoiceStateTarget(target, invoiceStateId);
	}
	
	public LanguagesTarget languages() {
		return new LanguagesTarget(target);
	}
	
	public LanguageTarget language(String languageId) {
		return new LanguageTarget(target, languageId);
	}
	
	public CurrenciesTarget currencies() {
		return new CurrenciesTarget(target);
	}
	
	public CurrencyTarget currency(String currencyId) {
		return new CurrencyTarget(target, currencyId);
	}

	public MerchantsTarget merchants() {
		return new MerchantsTarget(target);
	}
	
	public MerchantTarget merchant(String merchantId) {
		return new MerchantTarget(target, merchantId);
	}

	public PaymentGatewayProvidersTarget paymentGatewayProviders() {
		return new PaymentGatewayProvidersTarget(target);
	}
	
	public PaymentGatewayProviderTarget paymentGatewayProvider(String paymentGatewayProviderId) {
		return new PaymentGatewayProviderTarget(target, paymentGatewayProviderId);
	}

}
