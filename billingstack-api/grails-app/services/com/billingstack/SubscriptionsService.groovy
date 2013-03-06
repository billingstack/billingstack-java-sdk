package com.billingstack

class SubscriptionsService {

	def map(subscription) {
		def entity = [
			id : subscription.id,
			customer_id : subscription.customer.id,
			plan_id : subscription.plan.id,
			resource : subscription.resource,
			billing_day : subscription.billingDay
		]
		if(subscription.paymentMethod) {
			entity.payment_method = subscription.paymentMethod.id
		}
		entity
	}

	def list() {
		Subscription.list().collect { map(it) }
	}

	def create(String merchantId, String customerId, entity) {
		def subscription = Subscription.newInstance(
			customer : Customer.load(customerId),
			paymentMethod : CustomerPaymentMethod.load(entity.payment_method),
			plan : Plan.load(entity.plan_id),
			resource : entity.resource,
			billingDay : new Date().date
		)
		map(subscription.save(flush : true, failOnError : true))
	}

	def show(String subscriptionId) {
		map(Subscription.get(subscriptionId))
	}

	def update(String merchantId, String customerId, String subscriptionId, entity) {
		def subscription = Subscription.get(subscriptionId)
		subscription.resource = entity.resource
		map(subscription)
	}

	def delete(String subscriptionId) {
		Subscription.load(subscriptionId).delete(flush : true)
	}

}
