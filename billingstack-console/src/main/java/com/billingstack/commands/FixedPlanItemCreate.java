package com.billingstack.commands;

import java.math.BigDecimal;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.FixedPlanItem;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;
import com.google.common.collect.Lists;

public class FixedPlanItemCreate extends MerchantCommand {
	
	public FixedPlanItemCreate() {
		super("plan-item-fixed-create");
	}

	@Override
	public void execute(Environment env, final CommandLine cmd) {
		
		final FixedPlanItem planItem = (FixedPlanItem) getMerchant(env, cmd).plan(cmd.getOptionValue("plan")).item(cmd.getOptionValue("product")).create(new FixedPlanItem(){{
			setTitle(cmd.getOptionValue("title"));
			setPrice(new BigDecimal(cmd.getOptionValue("price")));
		}});
		
		Table t = new Table(new TableModel<FixedPlanItem>(Lists.newArrayList(planItem)) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 36, Column.ALIGN_LEFT),
					new Column("provider", 16, Column.ALIGN_LEFT),
					new Column("source", 16, Column.ALIGN_LEFT),
					new Column("product", 32, Column.ALIGN_LEFT),
					new Column("price", 6, Column.ALIGN_LEFT)
				};
			}

			@Override
			public String[][] getRows() {
				String[][] rows = new String[data.size()][];
				for(int i = 0; i < data.size(); i++) {
					rows[i] = new String[]{
						data.get(i).getId(),
						data.get(i).getProvider(),
						data.get(i).getSource(),
						data.get(i).getName(),
						data.get(i).getPrice().toString()
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
		opts.addOption(null, "plan", true, "plan id");
		opts.addOption(null, "product", true, "product id");
		opts.addOption(null, "title", true, "title");
		opts.addOption(null, "description", true, "description");
		opts.addOption(null, "price", true, "price [only for fixed price plan item]");
		return opts;
	}
	
}
