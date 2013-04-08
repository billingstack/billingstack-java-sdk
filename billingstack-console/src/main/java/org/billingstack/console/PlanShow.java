package org.billingstack.console;

import org.apache.commons.cli.CommandLine;
import org.billingstack.MerchantTarget;
import org.billingstack.Plan;
import org.billingstack.PlanItem;
import org.openstack.console.utils.Column;
import org.openstack.console.utils.Table;
import org.openstack.console.utils.TableModel;

import com.google.common.collect.Lists;

public class PlanShow extends MerchantCommand {
	
	public PlanShow() {
		super("plan-show");
	}

	@Override
	public void execute(MerchantEnvironment env, CommandLine cmd) {
		
		String[] args = cmd.getArgs();
		if(args.length == 1) {
			
			final Plan plan = env.ENDPOINT.plan(args[0]).show();
			
			Table t = new Table(new TableModel<Plan>(Lists.newArrayList(plan)) {

				@Override
				public Column[] getHeaders() {
					return new Column[]{
						new Column("id", 36, Column.ALIGN_LEFT),
						new Column("name", 32, Column.ALIGN_LEFT),
						new Column("title", 32, Column.ALIGN_LEFT)
					};
				}

				@Override
				public String[] getRow(Plan plan) {
						return new String[]{
							plan.getId(),
							plan.getName(),
							plan.getTitle()
						};
				}
				
			});
			System.out.println(t.render());
			
			System.out.println("ITEMS");
			
			Table itemsTable = new Table(new TableModel<PlanItem>(plan.getItems()) {

				@Override
				public Column[] getHeaders() {
					return new Column[]{
						new Column("product", 32, Column.ALIGN_LEFT),
						new Column("provider", 16, Column.ALIGN_LEFT),
						new Column("source", 16, Column.ALIGN_LEFT),
						new Column("name", 32, Column.ALIGN_LEFT)
					};
				}

				@Override
				public String[] getRow(PlanItem planItem) {
					return new String[]{
						planItem.getProductId(),
						planItem.getProvider(),
						planItem.getSource(),
						planItem.getName()
					};
				}
			});
			
			System.out.println(itemsTable.render());
			
		}
		
	}
	
}
