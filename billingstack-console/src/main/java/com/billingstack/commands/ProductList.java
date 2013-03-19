package com.billingstack.commands;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.billingstack.Product;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;

public class ProductList extends MerchantCommand {
	
	public ProductList() {
		super("product-list");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		
		final List<Product> products = getMerchant(env, cmd).products().list();
		
		Table t = new Table(new TableModel<Product>(products) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 36, Column.ALIGN_LEFT),
					new Column("name", 16, Column.ALIGN_LEFT),
					new Column("title", 32, Column.ALIGN_LEFT)
				};
			}

			@Override
			public String[] getRow(Product product) {
				return new String[]{
						product.getId(),
						product.getName(),
						product.getTitle()
				};
			}
		});
		System.out.println(t.render());
	}

	
}
