package org.billingstack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.WebTarget;

public class FilterQuery {
	
	protected List<String> fields = new ArrayList<String>();
	
	protected List<String> ops = new ArrayList<String>();
	
	protected List<Serializable> values = new ArrayList<Serializable>();

	public FilterQuery() {
		
	}
	
	private FilterQuery filter(String field, String op, Serializable value) {
		fields.add(field);
		ops.add(op);
		values.add(value);
		return (FilterQuery) this;
	}
	
	public FilterQuery lt(String field, Serializable value) {
		return filter(field, "lt", value);
	}
	
	public FilterQuery le(String field, Serializable value) {
		return filter(field, "le", value);
	}
	
	public FilterQuery eq(String field, Serializable value) {
		return filter(field, "eq", value);
	}
	
	public FilterQuery ne(String field, Serializable value) {
		return filter(field, "ne", value);
	}
	
	public FilterQuery ge(String field, Serializable value) {
		return filter(field, "ge", value);
	}
	
	public FilterQuery gt(String field, Serializable value) {
		return filter(field, "gt", value);
	}
	
	public WebTarget query(WebTarget target) {
		if(fields.size() > 0) {
			target = target.queryParam("q.field", fields.toArray());
			target = target.queryParam("q.op", ops.toArray());
			target = target.queryParam("q.value", values.toArray());
		}
		return target;
	}
	
}
