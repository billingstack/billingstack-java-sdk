package com.billingstack

import grails.converters.JSON

class PlansService {
	
	def planItemsService

	def map(plan) {
		def entity = [
			id : plan.id,
			merchant_id : plan.merchant.id,
			name : plan.name,
			title : plan.title,
			description : plan.description
		]
		entity.items = plan.items.collect {
			planItemsService.map(it)
		}
		entity.quotas = JSON.parse(plan.quotasJson)
		entity
	}

	def list(String merchantId) {
		Plan.createCriteria().list {
			eq 'merchant.id', merchantId
		}.collect { map(it) }
	}

	def create(merchantId, entity) {
		def plan = Plan.newInstance(
			merchant : Merchant.load(merchantId),
			name : entity.name,
			title : entity.title,
			description : entity.description,
			quotasJson : (entity["quotas"] as JSON).toString()
		)
		map(plan.save(flush : true, failOnError : true))
	}

	def show(String planId) {
		map(Plan.get(planId))
	}

	def update(String planId, entity) {
		def plan = Plan.get(planId)
		plan.name = entity.name
		plan.title = entity.title
		plan.description = entity.description
		plan.quotasJson = (entity["quotas"] as JSON).toString()
		map(plan)
	}

	def delete(String planId) {
		Plan.load(planId).delete(flush : true)
	}

}
