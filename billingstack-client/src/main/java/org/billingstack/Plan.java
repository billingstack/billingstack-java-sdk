package org.billingstack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonProperty;

public class Plan {

	private String id;
	
	@JsonProperty("merchant_id")
	private String merchant;

	private String name;
	
	private String title;
	
	private String description;
	
	private List<PlanItem> items = new ArrayList<PlanItem>();
	
	
	private Map<String, Object> properties;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the items
	 */
	public List<PlanItem> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<PlanItem> items) {
		this.items = items;
	}

	/**
	 * @return the properties
	 */
	@JsonAnyGetter
	public Map<String, Object> getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	@JsonAnySetter
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Plan [id=" + id + ", merchant=" + merchant + ", name=" + name
				+ ", title=" + title + ", description=" + description
				+ ", items=" + items + ", properties=" + properties + "]";
	}

	
	
}
