package com.billingstack.utils;

import java.util.Collection;

public abstract class TableModel<T> {
	
	private Collection<T> data;
	
	public TableModel(Collection<T> data) {
		this.data = data;
	}
	
	public abstract Column[] getHeaders();

	public abstract String[][] getRows();
	
}
