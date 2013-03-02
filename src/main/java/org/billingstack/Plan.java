package org.billingstack;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Plan {

	private String id;
	
	@JsonProperty("merchant_id")
	private String merchant;

	private String name;
	
	private String title;
	
	private String description;
	
	private List<PlanItem> items = new ArrayList<PlanItem>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
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

	public List<PlanItem> getItems() {
		return items;
	}

	public void setItems(List<PlanItem> items) {
		this.items = items;
	}
	
}
