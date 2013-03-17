package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.Currency;
import org.billingstack.InvoiceState;
import org.billingstack.Language;
import org.billingstack.Merchant;
import org.billingstack.PaymentGatewayProvider;
import org.billingstack.Role;

import com.billingstack.Environment;

public class Clean extends Command {

	public Clean() {
		super("clean");
	}

	@Override
	public void execute(Environment env, final CommandLine cmd) {
		
		BillingStackEndpoint bs = env.getBillingStack();
		
		for(Merchant merchant : bs.merchants().list()) {
			bs.merchant(merchant.getId()).delete();
		}
		
		for(Role role : bs.roles().list()) {
			bs.role(role.getId()).delete();
		}
		for(Language language : bs.languages().list()) {
			bs.language(language.getName()).delete();
		}
		for(Currency currency : bs.currencies().list()) {
			bs.currency(currency.getName()).delete();
		}
		for(InvoiceState invoiceState : bs.invoiceStates().list()) {
			bs.invoiceState(invoiceState.getName());
		}
		for(PaymentGatewayProvider paymentGatewayProvider : bs.paymentGatewayProviders().list()) {
			bs.paymentGatewayProvider(paymentGatewayProvider.getId()).delete();
		}
		
		
	}
	
}
