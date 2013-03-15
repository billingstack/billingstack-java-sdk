package org.billingstack;

import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
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
	
	static {
		DEFAULT_MAPPER.setSerializationInclusion(Inclusion.NON_NULL);
		DEFAULT_MAPPER.enable(SerializationConfig.Feature.INDENT_OUTPUT);
		DEFAULT_MAPPER.enable(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
	}
	
	private final Client CLIENT;

	private WebTarget target;
	
	private Access access;
	
	private LoggingFilter loggingFilter;
	
	public BillingStack() {
		
		ClientConfig cc = new ClientConfig();
		//CLIENT = ClientBuilder.newClient(cc.connector(new GrizzlyConnector(cc.getConfiguration())));
		CLIENT = ClientBuilder.newClient(cc.connector(new ApacheConnector(cc.getConfiguration())));
		//CLIENT = ClientBuilder.newClient();
		//CLIENT.register(new LoggingFilter(Logger.getLogger("billingstack"), 100000));
		CLIENT.register(new JacksonFeature()).register(new ContextResolver<ObjectMapper>() {

			public ObjectMapper getContext(Class<?> type) {
				return DEFAULT_MAPPER;
			}

		});
		
		//target = CLIENT.target(endpoint);
	}
	
	public BillingStackEndpoint create(String endpoint) {
		WebTarget target = CLIENT.target(endpoint);
		if(loggingFilter != null) {
			target.register(loggingFilter);
		}
		/*
		if(token != null) {
			target.register(tokenFilter);
		}
		*/
		return new BillingStackEndpoint(target);
	}
	
	public void enableLogging(Logger logger, int entitySize) {
		loggingFilter = new LoggingFilter(logger, entitySize);
	}
	
	public void disableLogging() {
		loggingFilter = null;
	}
	
	public void close() {
		CLIENT.close();
	}
	
	public void authenticate(Authentication authentication) {
		this.access = target.path("/tokens").request().post(Entity.json(authentication),Access.class);
	}
	
	public void exit() {
		this.target.path("/tokens").request().delete();
	}
	
}
