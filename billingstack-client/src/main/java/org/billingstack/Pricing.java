package org.billingstack;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

@JsonTypeInfo(use=Id.NAME, include=As.PROPERTY, property="type")
@JsonSubTypes({
    @JsonSubTypes.Type(value=FixedPricing.class, name="fixed"),
    @JsonSubTypes.Type(value=VolumeRangePricing.class, name="volume"),
}) 
public abstract class Pricing {

}
