package org.billingstack.console;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.Customer;
import org.billingstack.MerchantTarget;

import com.google.common.collect.Lists;
import com.woorea.openstack.console.utils.Column;
import com.woorea.openstack.console.utils.Table;
import com.woorea.openstack.console.utils.TableModel;

public class CustomerCreate extends MerchantCommand {
	
	public CustomerCreate() {
		super("customer-create");
	}

	@Override
	public void execute(MerchantEnvironment env, final CommandLine cmd) {
		
		//grants en keystone
				
		final Customer customer = env.ENDPOINT.customers().create(new Customer(){{
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

	
	/* (non-Javadoc)
	 * @see com.billingstack.commands.Command#getOptions()
	 */
	@Override
	public Options getOptions() {
		Options opts = super.getOptions();
		opts.addOption(null, "name", true, "customer name");
		opts.addOption(null, "language", true, "language");
		opts.addOption(null, "currency", true, "currency");
		return opts;
	}
	
}
