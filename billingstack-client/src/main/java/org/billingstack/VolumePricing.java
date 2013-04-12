package org.billingstack;

import java.math.BigDecimal;
import java.util.List;

import org.codehaus.jackson.annotate.JsonTypeName;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

@JsonTypeName("volume")
public class VolumePricing extends Pricing {

	private List<VolumeTier> tiers;

	/**
	 * @return the tiers
	 */
	public List<VolumeTier> getTiers() {
		return tiers;
	}

	/**
	 * @param tiers the tiers to set
	 */
	public void setTiers(List<VolumeTier> tiers) {
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
		VolumeTier tier = Iterables.find(tiers, new Predicate<VolumeTier>() {
			
			public boolean apply(final VolumeTier tier) {
				return ((tier.getFrom() == null || tier.getFrom().doubleValue() <= usage.getVolume().doubleValue()) && (tier.getTo() == null || usage.getVolume().doubleValue() <= tier.getTo().doubleValue()));
			}
			
		});
		return tier.getPrice();
	}
	
}
