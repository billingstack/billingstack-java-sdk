package org.billingstack;

import javax.ws.rs.client.WebTarget;

public class LanguageTarget {
	
	private WebTarget target;

	public LanguageTarget(WebTarget target, String invoiceId) {
		this.target = target.path("languages").path(invoiceId);
	}
	
	public Language show() {
		return target.request().get(Language.class);
	}
	
	public void delete() {
		target.request().delete();
	}
	
}
