package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.Plan;
import org.billingstack.PlanItem;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;
import com.google.common.collect.Lists;

public class PlanShow extends MerchantCommand {
	
	public PlanShow() {
		super("plan-show");
	}

	@Override
	public void execute(Environment env, final CommandLine cmd) {
		
		final Plan plan = getMerchant(env, cmd).plan(cmd.getOptionValue("plan")).show();
		
		Table t = new Table(new TableModel<Plan>(Lists.newArrayList(plan)) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 36, Column.ALIGN_LEFT),
					new Column("name", 16, Column.ALIGN_LEFT),
				};
			}

			@Override
			public String[][] getRows() {
				String[][] rows = new String[data.size()][];
				for(int i = 0; i < data.size(); i++) {
					rows[i] = new String[]{
						data.get(i).getId(),
						data.get(i).getName()
					};
				}
				return rows;
			}
		});
		System.out.println(t.render());
		
		System.out.println("ITEMS");
		
		Table itemsTable = new Table(new TableModel<PlanItem>(plan.getItems()) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 32, Column.ALIGN_LEFT),
					new Column("provider", 16, Column.ALIGN_LEFT),
					new Column("source", 16, Column.ALIGN_LEFT),
					new Column("product", 32, Column.ALIGN_LEFT)
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
						data.get(i).getName()
					};
				}
				return rows;
			}
		});
		
		System.out.println(itemsTable.render());
		
	}

	
	/* (non-Javadoc)
	 * @see com.billingstack.commands.Command#getOptions()
	 */
	@Override
	public Options getOptions() {
		Options opts = super.getOptions();
		opts.addOption(null, "plan", true, "plan id");
		return opts;
	}
	
}
