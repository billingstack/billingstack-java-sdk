package com.billingstack

class PaymentGatewaysService {
	
	def map(paymentGateway) {
		def entity = [
			id : paymentGateway.id,
			title : paymentGateway.title,
			provider : paymentGateway.provider.id
		]
		//if(paymentGateway.provider) entity.provider = paymentGateway.provider.id
	}

	def list() {
		PaymentGateway.list().collect { map(it) }
	}

	def create(merchantId, entity) {
		def paymentGateway = PaymentGateway.newInstance(
			merchant : Merchant.load(merchantId),
			title : entity.title,
			provider : PaymentGatewayProvider.findByIdOrName(entity.provider, entity.provider)
		)
		map(paymentGateway.save(failOnError : true))
	}

	def show(String paymentGatewayId) {
		map(PaymentGateway.get(paymentGatewayId))
	}

	def update(entity) {
		def paymentGateway = PaymentGateway.get(entity.id)
		map(paymentGateway)
	}

	def delete(String paymentGatewayId) {
		PaymentGateway.load(paymentGatewayId).delete(flush : true)
	}

}
