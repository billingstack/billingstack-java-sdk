package com.billingstack.commands;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.Plan;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;

public class PlanList extends Command {

	@Override
	public void execute(Environment env, CommandLine cmd) {
		
		final List<Plan> plans = env.getBillingStack().merchant(cmd.getOptionValue('m')).plans().list();
		
		Table t = new Table(new TableModel<Plan>(plans) {

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
				String[][] rows = new String[plans.size()][];
				for(int i = 0; i < plans.size(); i++) {
					rows[i] = new String[]{
						plans.get(i).getId(),
						plans.get(i).getName(),
						plans.get(i).getTitle(),
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