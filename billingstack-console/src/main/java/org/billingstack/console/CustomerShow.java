package org.billingstack.console;

import org.apache.commons.cli.CommandLine;
import org.billingstack.Customer;
import org.billingstack.MerchantTarget;

import com.google.common.collect.Lists;
import com.woorea.openstack.console.utils.Column;
import com.woorea.openstack.console.utils.Table;
import com.woorea.openstack.console.utils.TableModel;

public class CustomerShow extends MerchantCommand {
	
	public CustomerShow() {
		super("customer-show");
	}

	@Override
	public void execute(MerchantEnvironment env, final CommandLine cmd) {
		
		String[] args = cmd.getArgs();
		if(args.length == 1) {
			
			Customer customer = env.ENDPOINT.customer(args[0]).show();
			
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
