package org.billingstack.services;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.logging.Logger;

import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.FilterQuery;
import org.billingstack.FixedPricing;
import org.billingstack.Merchant;
import org.billingstack.Plan;
import org.billingstack.PlanItem;
import org.billingstack.Pricing;
import org.billingstack.Subscription;
import org.billingstack.Usage;
import org.billingstack.VolumePricing;
import org.billingstack.VolumeTier;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

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
				for(final Usage usage :bs.merchant(merchant.getId()).usages().list(new FilterQuery().eq("subscription_id", subscription.getId()))) {
					PlanItem planItem = Iterables.find(plan.getItems(), new Predicate<PlanItem>() {
						
						public boolean apply(PlanItem planItem) {
							return planItem.getProductId().equals(usage.getProductId());
						}
						
					});
					System.out.println("Rating usage " + usage);
					System.out.println(planItem);
					for(Pricing p : planItem.getPricing()) {
						usage.setTotal(usage.getTotal().add(p.rate(usage)));
					}
					System.out.println("Rated usage " + usage);
				}
			}
		}
	}
	
	public static final void rate(final Usage usage, VolumePricing pricing) {
		VolumeTier tier = Iterables.find(pricing.getTiers(), new Predicate<VolumeTier>() {
			
			public boolean apply(final VolumeTier tier) {
				return ((tier.getFrom() == null || tier.getFrom().doubleValue() <= usage.getVolume().doubleValue()) && (tier.getTo() == null || usage.getVolume().doubleValue() <= tier.getTo().doubleValue()));
			}
			
		});
		usage.setTotal(tier.getPrice().multiply(usage.getVolume()));
	}
	
	public static final void rate(Usage usage, FixedPricing pricing) {
		
	}
	
	public static final void rate(Usage usage, Pricing pricing) {
		
		System.out.println("no rating");
	}
}
