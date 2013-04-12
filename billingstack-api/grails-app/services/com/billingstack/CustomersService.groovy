package com.billingstack

import grails.converters.JSON

class CustomersService {
	
	def plansService

	def map(customer) {
		def entity = [
			id : customer.id,
			merchant_id : customer.merchant.id,
			name : customer.name,
			title : customer.title
		]
		if(customer.language) entity.language = customer.language.name
		if(customer.currency) entity.currency = customer.currency.name
		entity
	}
	
	def list(String merchantId) {
		Customer.createCriteria().list {
			eq 'merchant.id', merchantId
		}.collect { map(it) }
	}

	def create(merchantId, entity) {
		def customer = Customer.newInstance(
			id : entity.id,
			merchant : Merchant.load(merchantId),
			name : entity.name,
			title : entity.title,
			language : Language.load(entity.language),
			currency : Currency.load(entity.currency)
		)
		/*
		customer.metadata = entity.metadata.collect { k, v ->
			Metadata.newInstance(key : k, value : v)
		}
		*/
		map(customer.save(failOnError : true))
	}

	def show(String merchantId, String customerId) {
		def customer = Customer.get(customerId)
		if(!customer) {
			throw new RuntimeException("not.found")
		}
		map(customer)
	}

	def update(entity) {
		def customer = Customer.get(entity.id)
		map(customer)
	}

	def delete(String customerId) {
		Customer.load(customerId).delete(flush : true)
	}
	
	def action(String merchantId, String customerId, entity) {
		if(entity.bill != null) {
			def customer = Customer.get(customerId)
			println "billing $customer.name"
			customer.subscriptions.each { subscription ->
				//def invoice = new Invoice()
				def plan = plansService.map(subscription.plan)
				println plan
				subscription.usage.groupBy { it.product }.each { k, usageList ->
					def planItem = plan.items.find { it.product_id == k.id }
					println planItem
					println "$k : $usageList"
					println usageList.sum { it.volume }
					usageList.each { usageItem ->
						//find price from planItem.pricing
						rate(usageItem, planItem.pricing)
					}
					/*
					def line = new InvoiceLine()
					def item = plan.items.find { k.equals(usage.product) }
					def total = v.sum { it.value }
					item.pricing ...
					*/
				}
			}
		} else if(entity.rate != null) {
			def customer = Customer.get(customerId)
			customer.subscriptions.each { subscription ->
				def plan = plansService.map(subscription.plan)
				subscription.usage.groupBy { it.product }.each { product, usageList ->
					def planItem = plan.items.find { it.product_id == k.id }
					usageList.each { usageItem ->
						//find price from planItem.pricing
						rate(usageItem, planItem.pricing)
					}
				}
			}
		}
	}

	private rate(usage, pricing) {
		usage.total = 0
		pricing.each { rule ->
			switch(rule.type) {
				case "fixed":
					usage.total += rule.price
					break
				case "volume":
					def tier = rule.tiers.find { new BigDecimal(it.from) <= usage.volume && usage.volume <= new BigDecimal(it.to) }
					if(tier) {
						println "tier found : $tier"
						println new BigDecimal(tier.price).multiply(usage.volume)
					} else {
						println "no tier found!!!"
					}
					break
				case "time":
					break
			}
		}
		println usage
	}
}
