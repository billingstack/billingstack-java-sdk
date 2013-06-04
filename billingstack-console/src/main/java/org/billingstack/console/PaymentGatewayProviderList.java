package org.billingstack.console;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.PaymentGatewayProvider;

import com.woorea.openstack.console.utils.Column;
import com.woorea.openstack.console.utils.Table;
import com.woorea.openstack.console.utils.TableModel;

public class PaymentGatewayProviderList extends BillingStackCommand {
	
	public PaymentGatewayProviderList() {
		super("payment-gateway-provider-list");
	}

	@Override
	public void execute(BillingStackEndpoint bs, CommandLine cmd) {
		final List<PaymentGatewayProvider> paymentGatewayProviders = bs.paymentGatewayProviders().list();
		
		Table t = new Table(new TableModel<PaymentGatewayProvider>(paymentGatewayProviders) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 36, Column.ALIGN_LEFT),
					new Column("name", 16, Column.ALIGN_LEFT),
					new Column("title", 32, Column.ALIGN_LEFT)
				};
			}

			@Override
			public String[] getRow(PaymentGatewayProvider paymentGatewayProvider) {
				return new String[]{
					paymentGatewayProvider.getId(),
					paymentGatewayProvider.getName(),
					paymentGatewayProvider.getTitle()
				};
			}
		});
		System.out.println(t.render());
	}

}
