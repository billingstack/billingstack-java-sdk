package org.billingstack;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class LanguagesTarget {

	private WebTarget target;

	public LanguagesTarget(WebTarget target) {
		this.target = target.path("languages");
	}
	
	public List<Language> list() {
		return target.request().get(new GenericType<List<Language>>(){});
	}
	
	public Language create(Language language) {
		return target.request().post(Entity.json(language),Language.class);
	}
	
}
