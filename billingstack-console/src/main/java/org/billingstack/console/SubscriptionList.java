package org.billingstack.console;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.MerchantTarget;
import org.billingstack.Subscription;

import com.google.common.collect.Lists;
import com.woorea.openstack.console.utils.Column;
import com.woorea.openstack.console.utils.Table;
import com.woorea.openstack.console.utils.TableModel;

public class SubscriptionList extends MerchantCommand {
	
	public SubscriptionList() {
		super("subscription-list");
	}

	@Override
	public void execute(MerchantEnvironment env, CommandLine cmd) {
		
		final List<Subscription> subscriptions = env.ENDPOINT.subscriptions().list();
		
		Table t = new Table(new TableModel<Subscription>(Lists.newArrayList(subscriptions)) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 32, Column.ALIGN_LEFT),
					new Column("customer", 32, Column.ALIGN_LEFT),
					new Column("plan", 32, Column.ALIGN_LEFT),
					new Column("resource", 32, Column.ALIGN_LEFT),
					new Column("payment method", 32, Column.ALIGN_LEFT),
					new Column("billingday", 2, Column.ALIGN_LEFT)
				};
			}

			@Override
			public String[] getRow(Subscription subscription) {
				return new String[]{
						subscription.getId(),
						subscription.getCustomerId(),
						subscription.getPlanId(),
						subscription.getResourceType() + ":" +
						subscription.getResourceId(),
						subscription.getPaymentMethod(),
						subscription.getBillingDay() != null ? subscription.getBillingDay().toString() : "null!" 
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
		opts.addOption(null, "customer", true, "customer id");
		return opts;
	}
	
}
