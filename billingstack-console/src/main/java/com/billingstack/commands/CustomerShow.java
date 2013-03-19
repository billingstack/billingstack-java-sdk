package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.billingstack.Customer;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;
import com.google.common.collect.Lists;

public class CustomerShow extends MerchantCommand {
	
	public CustomerShow() {
		super("customer-show");
	}

	@Override
	public void execute(Environment env, final CommandLine cmd) {
		
		String[] args = cmd.getArgs();
		if(args.length == 1) {
			
			Customer customer = getMerchant(env, cmd).customer(args[0]).show();
			
			Table t = new Table(new TableModel<Customer>(Lists.newArrayList(customer)) {

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
	
}
