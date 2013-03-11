package com.billingstack.utils;

import java.util.List;

public abstract class TableModel<T> {
	
	protected List<T> data;
	
	public TableModel(List<T> data) {
		this.data = data;
	}
	
	public abstract Column[] getHeaders();

	public abstract String[][] getRows();
	
}
