package com.billingstack

import grails.converters.JSON

class PaymentMethodsService {

	def map(paymentMethod) {
		[
			id : paymentMethod.id,
			//provider_id : paymentMethod.provider.id
			type : paymentMethod.type,
			name : paymentMethod.name,
			title : paymentMethod.title,
			properties : JSON.parse(paymentMethod.metadataJson)
		]
	}

	def list() {
		PaymentMethod.list().collect { map(it) }
	}

	def create(paymentGatewayProviderId, entity) {
		def paymentMethod = PaymentMethod.newInstance(
			provider : PaymentGatewayProvider.load(paymentGatewayProviderId),
			type : entity.title,
			name : entity.name,
			title : entity.title,
			metadataJson : (entity["properties"] as JSON).toString()
		)
		map(paymentMethod.save(failOnError : true))
	}

	def show(String paymentMethodId) {
		map(PaymentMethod.get(paymentMethodId))
	}

	def update(entity) {
		def paymentMethod = PaymentMethod.get(entity.id)
		map(paymentMethod)
	}

	def delete(String paymentMethodId) {
		PaymentMethod.load(paymentMethodId).delete(flush : true)
	}

}
