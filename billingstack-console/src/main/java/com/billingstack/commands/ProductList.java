package com.billingstack.commands;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.Product;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;

public class ProductList extends Command {

	@Override
	public void execute(Environment env, CommandLine cmd) {
		
		final List<Product> products = env.getBillingStack().merchant(cmd.getOptionValue('m')).products().list();
		
		Table t = new Table(new TableModel<Product>(products) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 16, Column.ALIGN_LEFT),
					new Column("name", 16, Column.ALIGN_LEFT),
					new Column("title", 32, Column.ALIGN_LEFT),
				};
			}

			@Override
			public String[][] getRows() {
				String[][] rows = new String[products.size()][];
				for(int i = 0; i < products.size(); i++) {
					rows[i] = new String[]{
						products.get(i).getId(),
						products.get(i).getName(),
						products.get(i).getTitle(),
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
		opts.addOption("m", "merchant", true, "merchant id");
		return opts;
	}
	
}
