package org.billingstack;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

@JsonTypeInfo(use=Id.NAME, include=As.PROPERTY, property="type")
@JsonSubTypes({
    @JsonSubTypes.Type(value=FixedPricing.class, name="fixed"),
    @JsonSubTypes.Type(value=VolumePricing.class, name="volume"),
}) 
public abstract class Pricing {

	public abstract BigDecimal rate(Usage usage);
	
}
