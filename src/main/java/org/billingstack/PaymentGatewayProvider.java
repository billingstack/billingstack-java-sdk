package org.billingstack;

import java.util.List;
import java.util.Map;

public class PaymentGatewayProvider {
	
	private String id;

	private String name;

	private String title;

	private String description;

	private Boolean isDefault;

	private Map<String,String> metadata;
	
	private List<PaymentMethod> methods;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean isDefault() {
		return isDefault;
	}

	public void setDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public List<PaymentMethod> getMethods() {
		return methods;
	}

	public void setMethods(List<PaymentMethod> methods) {
		this.methods = methods;
	}
	
}
