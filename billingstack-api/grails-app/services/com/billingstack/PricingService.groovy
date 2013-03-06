package com.billingstack

class PricingService {

	def map(pricing) {
		[
			id : pricing.id,
			start : pricing.start,
			end : pricing.end,
			price : pricing.price
		]
	}

	def list() {
		Pricing.list().collect { map(it) }
	}

	def create(planItemId, entity) {
		
		def planItem = PlanItem.get(planItemId)
		
		def pricing
		
		if(planItem instanceof VolumePricingPlanItem) {
			pricing = VolumeRangePricing.newInstance(
				item : planItem,
				start : entity.start,
				end : entity.end,
				price : entity.price
			)
		} else if(planItem instanceof TimePricingPlanItem) {
			pricing = TimeRangePricing.newInstance(
				item : planItem,
				start : entity.start,
				end : entity.end,
				price : entity.price
			)
		}
		
		map(pricing.save(failOnError : true))
	}

	def show(String pricingId) {
		map(Pricing.get(planItemId))
	}

	def update(entity) {
		def planItem = Pricing.get(entity.id)
		map(planItem)
	}

	def delete(String pricingId) {
		Pricing.load(pricingId).delete(flush : true)
	}

}
