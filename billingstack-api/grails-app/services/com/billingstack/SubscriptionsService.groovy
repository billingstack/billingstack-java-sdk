package com.billingstack

class SubscriptionsService {

	def map(subscription) {
		def entity = [
			id : subscription.id,
			customer_id : subscription.customer.id,
			customer_name : subscription.customer.name,
			customer_title : subscription.customer.title,
			plan_id : subscription.plan.id,
			plan_name : subscription.plan.name,
			plan_title : subscription.plan.title,
			resource_type : subscription.resourceType,
			resource_id : subscription.resourceId,
			billing_day : subscription.billingDay
		]
		if(subscription.paymentMethod) {
			entity.payment_method_id = subscription.paymentMethod.id
		}
		entity
	}

	def list(String merchantId, filters) {
		def ops = filters.list('q.op')
		def names = filters.list('q.field')
		def values = filters.list('q.value')
		Subscription.createCriteria().list {
			customer {
				eq 'merchant.id', merchantId
			}
			for(int i = 0; i < ops.size(); i++) {
				if("customer_id".equals(names.get(i))) {
					customer {
						"${ops.get(i)}"('id',values.get(i))
					}
				}
			}
		}.collect { map(it) }
	}

	def create(String merchantId, entity) {
		def subscription = Subscription.newInstance(
			customer : Customer.load(entity.customer_id),
			paymentMethod : CustomerPaymentMethod.load(entity.payment_method_id),
			plan : Plan.load(entity.plan_id),
			resourceType : entity.resource_type,
			resourceId : entity.resource_id,
			billingDay : new Date().date
		)
		map(subscription.save(flush : true, failOnError : true))
	}

	def show(String merchantId, String subscriptionId) {
		map(Subscription.get(subscriptionId))
	}

	def update(String merchantId, String subscriptionId, entity) {
		def subscription = Subscription.get(subscriptionId)
		subscription.resource = entity.resource
		map(subscription)
	}

	def delete(String merchantId, String subscriptionId) {
		Subscription.load(subscriptionId).delete(flush : true)
	}

}
