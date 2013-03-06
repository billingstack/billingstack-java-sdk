package com.billingstack

import grails.converters.JSON

class PaymentGatewayProvidersService {
	
	def map(paymentGatewayProvider) {
		[
			id : paymentGatewayProvider.id,
			properties : JSON.parse(paymentGatewayProvider.metadataJson)
		]
	}

	def list() {
		PaymentGatewayProvider.list().collect { map(it) }
	}

	def create(entity) {
		
		def paymentGatewayProvider = PaymentGatewayProvider.newInstance(
			name : entity.name,
			title : entity.title,
			description : entity.description,
			metadataJson : (entity["properties"] as JSON).toString()
		)
		map(paymentGatewayProvider.save(failOnError : true))
		
	}

	def show(String paymentGatewayProviderId) {
		map(PaymentGatewayProvider.get(paymentGatewayProviderId))
	}

	def update(entity) {
		def paymentGatewayProvider = PaymentGatewayProvider.get(entity.id)
		map(paymentGatewayProvider)
	}

	def delete(String paymentGatewayProviderId) {
		PaymentGatewayProvider.load(paymentGatewayProviderId).delete(flush : true)
	}

}
