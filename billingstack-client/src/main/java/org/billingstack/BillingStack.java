package org.billingstack;

import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.ext.ContextResolver;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.glassfish.jersey.apache.connector.ApacheConnector;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;

public class BillingStack {
	
	public static final ObjectMapper DEFAULT_MAPPER = new ObjectMapper();
	
	public static final Client CLIENT;
	
	static {
		DEFAULT_MAPPER.setSerializationInclusion(Inclusion.NON_NULL);
		DEFAULT_MAPPER.enable(SerializationConfig.Feature.INDENT_OUTPUT);
		DEFAULT_MAPPER.enable(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		
		ClientConfig cc = new ClientConfig();
		CLIENT = ClientBuilder.newClient(cc.connector(new ApacheConnector(cc.getConfiguration())));
		CLIENT.register(new LoggingFilter(Logger.getLogger("billingstack"), true));
		CLIENT.register(new JacksonFeature()).register(new ContextResolver<ObjectMapper>() {

			public ObjectMapper getContext(Class<?> type) {
				return DEFAULT_MAPPER;
			}

		});
	}

	private WebTarget target;
	
	public BillingStack(String endpoint) {
		this.target = CLIENT.target(endpoint);
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
