package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.Merchant;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;
import com.google.common.collect.Lists;

public class MerchantCreate extends Command {
	
	public MerchantCreate() {
		super("merchant-create");
	}

	@Override
	public void execute(Environment env, final CommandLine cmd) {
		
		final Merchant merchant = env.getBillingStack().merchants().create(new Merchant(){{
			setName(cmd.getOptionValue("name"));
			setTitle(cmd.getOptionValue("title"));
			setLanguage(cmd.getOptionValue("language"));
			setCurrency(cmd.getOptionValue("currency"));
		}});
		
		Table t = new Table(new TableModel<Merchant>(Lists.newArrayList(merchant)) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 36, Column.ALIGN_LEFT),
					new Column("name", 16, Column.ALIGN_LEFT),
					new Column("language", 8, Column.ALIGN_RIGHT),
					new Column("currency", 8, Column.ALIGN_RIGHT),
				};
			}

			@Override
			public String[] getRow(Merchant merchant) {
				return new String[]{
					merchant.getId(),
					merchant.getName(),
					merchant.getTitle(),
					merchant.getLanguage(),
					merchant.getCurrency()
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
		opts.addOption(null, "id", true, "merchant id");
		opts.addOption(null, "name", true, "merchant name");
		opts.addOption(null, "title", true, "merchant title");
		opts.addOption(null, "currency", true, "merchant currency");
		opts.addOption(null, "language", true, "merchant language");
		return opts;
	}

	

}
