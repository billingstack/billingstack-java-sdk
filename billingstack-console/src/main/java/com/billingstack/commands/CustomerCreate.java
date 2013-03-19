package com.billingstack.commands;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.Account;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.Customer;
import org.billingstack.Role;
import org.billingstack.User;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class CustomerCreate extends MerchantCommand {
	
	public CustomerCreate() {
		super("customer-create");
	}

	@Override
	public void execute(Environment env, final CommandLine cmd) {
		
		//grants en keystone
				
		final Customer customer = getMerchant(env, cmd).customers().create(new Customer(){{
			setId(cmd.getOptionValue("id"));
			setName(cmd.getOptionValue("name"));
			setTitle(cmd.getOptionValue("title"));
			setLanguage(cmd.getOptionValue("language"));
			setCurrency(cmd.getOptionValue("currency"));
		}});
		
		Table t = new Table(new TableModel<Customer>(Lists.newArrayList(customer)) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 36, Column.ALIGN_LEFT),
					new Column("name", 16, Column.ALIGN_LEFT),
					new Column("title", 32, Column.ALIGN_LEFT),
				};
			}

			@Override
			public String[] getRow(Customer customer) {
				return new String[]{
					customer.getId(),
					customer.getName(),
					customer.getTitle()
				};
			}
		});
		System.out.println(t.render());
	}

	
	/* (non-Javadoc)
	 * @see com.billingstack.commands.Command#getOptions()
	 */
	@Override
	public Options getOptions() {
		Options opts = super.getOptions();
		opts.addOption(null, "id", true, "account id");
		opts.addOption(null, "name", true, "customer name");
		opts.addOption(null, "language", true, "language");
		opts.addOption(null, "currency", true, "currency");
		return opts;
	}
	
}
