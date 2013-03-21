package com.billingstack;

import java.util.HashMap;
import java.util.Map;

import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;

import com.billingstack.utils.Console;
import com.google.common.collect.ImmutableMap;

public class Environment {
	
	private BillingStack client = new BillingStack();
	
	private Map<String, Object> properties = new HashMap<String, Object>();
	
	public Environment() {
		properties.put("billingstack.console.prompt", new Console().green("billingstack> ").toString());
		properties.put("billingstack.console.logging", Boolean.TRUE);
		properties.put("billingstack.endpoint", "http://localhost:8080/billingstack-api");
		properties.put("keystone.endpoint", "http://localhost:35357/v2.0");
		properties.put("keystone.username", "admin");
		properties.put("keystone.password", "secret0");
		properties.put("keystone.tenant_name", "admin");
		properties.put("identity.endpoint", "http://localhost:5000/v2.0");
		properties.put("nova.endpoint", "http://localhost:8774/v2");
	}

	public BillingStackEndpoint getBillingStack() {
		BillingStackEndpoint target = client.create((String) getProperty("billingstack.endpoint"));
		if((Boolean) getProperty("billingstack.console.logging")) {
			target.logger();
		}
		return target;
	}
	
	public void destroy() {
		client.close();
	}
	
	public void setProperty(String property, String value) {
		properties.put(property, value);
	}
	
	public <T> T getProperty(String property) {
		return (T) properties.get(property);
	}
	
	public Map<String, Object> getProperties() {
		return ImmutableMap.copyOf(properties);
	}
	
	public String getPrompt() {
		Console console = new Console();
		console.green("billingstack");
		if(getProperty("billingstack.console.merchant") != null) {
			console.yellow(":");
			console.green((String) getProperty("billingstack.console.merchant"));
		}
		if(getProperty("billingstack.console.customer") != null) {
			console.yellow(":");
			console.green((String) getProperty("billingstack.console.customer"));
		}
		console.green("> ");
		return console.toString();
	}
	
}
