package org.billingstack;

import javax.ws.rs.client.WebTarget;

public class UsageTarget {
	
	private WebTarget target;

	public UsageTarget(WebTarget target, String usageId) {
		this.target = target.path("usage").path(usageId);
	}
	
	public Usage show() {
		return target.request().get(Usage.class);
	}
	
	public void delete() {
		target.request().delete();
	}
	
}
