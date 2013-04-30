package org.billingstack.services;

import java.io.FileInputStream;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.FilterQuery;
import org.billingstack.Merchant;
import org.billingstack.Plan;
import org.billingstack.PlanItem;
import org.billingstack.Pricing;
import org.billingstack.Subscription;
import org.billingstack.Usage;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

public class RatingService {

	public static void main(String[] args) throws Exception {
		
		Properties properties = new Properties();
		properties.load(new FileInputStream("src/main/resources/billingstack.properties"));
		
		BillingStack client = new BillingStack(properties);
		client.enableLogging(Logger.getLogger(RatingService.class.getCanonicalName()), 10000);
		BillingStackEndpoint bs = client.create();
		
		for(Merchant merchant : bs.merchants().list()) {
			System.out.println("Rating merchant " + merchant.getName());
			for(Subscription subscription : bs.merchant(merchant.getId()).subscriptions().list()) {
				System.out.println("Rating subscription " + subscription.getPlanName());
				Plan plan = bs.merchant(merchant.getId()).plan(subscription.getPlanId()).show();
				System.out.println(plan);
				List<Usage> usageList = bs.merchant(merchant.getId()).usages().list(new FilterQuery().eq("subscription_id", subscription.getId()));
				Multimap<String, Usage> productsUsageList = Multimaps.index(usageList, new Function<Usage, String>() {
					public String apply(final Usage usage) {
					    return usage.getProductId();
					  }
				});
				for(final String productId : productsUsageList.keySet()) {
					PlanItem planItem = Iterables.find(plan.getItems(), new Predicate<PlanItem>() {
						
						public boolean apply(PlanItem planItem) {
							return planItem.getProductId().equals(productId);
						}
						
					});
					Collection<Usage> productUsageList = productsUsageList.get(productId);
					for(Pricing p : planItem.getPricing()) {
						
					}
					/*
					System.out.println("Rating usage " + usage);
					System.out.println(planItem);
					
						usage.setTotal(usage.getTotal().add(p.rate(usage)));
					}
					System.out.println("Rated usage " + usage);
					*/
				}
			}
		}
	}

}
