package com.billingstack

import grails.converters.JSON

class PlanItemsService {
	
	def map(planItem) {
		def entity = [
			plan_id : planItem.plan.id,
			product_id : planItem.product.id,
			name : planItem.product.name,
			title : planItem.title,
			description : planItem.description,
			pricing : JSON.parse(planItem.pricingJson)
		]
		entity
	}

	def create(planId, productId, entity) {
		def planItem = PlanItem.newInstance(
			plan : Plan.load(planId),
			product : Product.load(productId),
			title : entity.title,
			description : entity.description,
			pricingJson : "[]"
		)
		map(planItem.save(flush : true, failOnError : true))
	}
	
	def update(planId, productId, entity) {
		def planItem = PlanItem.findByPlanAndProduct(
			Plan.load(planId), Product.load(productId)
		)
		planItem.title = entity.title
		planItem.description = entity.description
		planItem.pricingJson = (entity["pricing"] as JSON).toString()
		map(planItem.save(flush : true, failOnError : true))
	}

	def delete(String planId, String productId) {
		PlanItem.findByPlanAndProduct(
			Plan.load(planId), Product.load(productId)
		).delete(flush : true)
	}

}
