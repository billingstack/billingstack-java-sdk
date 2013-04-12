package org.billingstack;

import java.math.BigDecimal;
import java.util.List;

import org.codehaus.jackson.annotate.JsonTypeName;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

@JsonTypeName("volume")
public class TimePricing extends Pricing {

	private List<TimeTier> tiers;

	/**
	 * @return the tiers
	 */
	public List<TimeTier> getTiers() {
		return tiers;
	}

	/**
	 * @param tiers the tiers to set
	 */
	public void setTiers(List<TimeTier> tiers) {
		this.tiers = tiers;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VolumePricing [tiers=" + tiers + "]";
	}
	
	public BigDecimal rate(final Usage usage) {
		TimeTier tier = Iterables.find(tiers, new Predicate<TimeTier>() {
			
			public boolean apply(final TimeTier tier) {
				//return ((tier.getFrom() == null || tier.getFrom().doubleValue() <= usage.getVolume().doubleValue()) && (tier.getTo() == null || usage.getVolume().doubleValue() <= tier.getTo().doubleValue()));
				return true;
			}
			
		});
		return tier.getPrice();
	}
	
}
