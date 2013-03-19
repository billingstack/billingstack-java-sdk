package com.billingstack.commands;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.Customer;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;

public class CustomerList extends MerchantCommand {
	
	public CustomerList() {
		super("customer-list");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		
		final List<Customer> customers = getMerchant(env, cmd).customers().list();
		
		Table t = new Table(new TableModel<Customer>(customers) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 36, Column.ALIGN_LEFT),
					new Column("name", 16, Column.ALIGN_LEFT),
					new Column("title", 32, Column.ALIGN_LEFT),
					new Column("language", 8, Column.ALIGN_RIGHT),
					new Column("currency", 8, Column.ALIGN_RIGHT)
				};
			}

			@Override
			public String[] getRow(Customer customer) {
				return new String[]{
					customer.getId(),
					customer.getName(),
					customer.getTitle(),
					customer.getLanguage(),
					customer.getCurrency()
				};
			}
		});
		System.out.println(t.render());
	}

	
}
