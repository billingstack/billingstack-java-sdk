package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.Customer;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;
import com.google.common.collect.Lists;

public class CustomerCreate extends MerchantCommand {
	
	public CustomerCreate() {
		super("customer-create");
	}

	@Override
	public void execute(Environment env, final CommandLine cmd) {
		
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
			public String[][] getRows() {
				String[][] rows = new String[data.size()][];
				for(int i = 0; i < data.size(); i++) {
					rows[i] = new String[]{
						data.get(i).getId(),
						data.get(i).getName(),
						data.get(i).getTitle(),
					};
				}
				return rows;
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
		opts.addOption(null, "name", true, "plan name");
		opts.addOption(null, "language", true, "language");
		opts.addOption(null, "currency", true, "currency");
		return opts;
	}
	
}
