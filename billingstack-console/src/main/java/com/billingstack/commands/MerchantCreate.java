package com.billingstack.commands;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.Merchant;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;
import com.google.common.collect.Lists;

public class MerchantCreate extends Command {

	@Override
	public void execute(Environment env, final CommandLine cmd) {
		
		final Merchant merchant = env.getBillingStack().merchants().create(new Merchant(){{
			setId(cmd.getOptionValue('i'));
			setName(cmd.getOptionValue('n'));
			setTitle(cmd.getOptionValue('t'));
			setLanguage(cmd.getOptionValue('l'));
			setCurrency(cmd.getOptionValue('c'));
		}});
		
		Table t = new Table(new TableModel<Merchant>(Lists.newArrayList(merchant)) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 32, Column.ALIGN_LEFT),
					new Column("name", 16, Column.ALIGN_LEFT),
					new Column("language", 8, Column.ALIGN_RIGHT),
					new Column("currency", 8, Column.ALIGN_RIGHT),
				};
			}

			@Override
			public String[][] getRows() {
				String[][] rows = new String[data.size()][];
				for(int i = 0; i < data.size(); i++) {
					rows[i] = new String[]{
						data.get(i).getId(),
						data.get(i).getName(),
						data.get(i).getLanguage(),
						data.get(i).getCurrency()
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
		opts.addOption("i", "id", true, "merchant id");
		opts.addOption("n", "name", true, "merchant name");
		opts.addOption("t", "title", true, "merchant title");
		opts.addOption("c", "currency", true, "merchant currency");
		opts.addOption("l", "language", true, "merchant language");
		return opts;
	}

	

}
