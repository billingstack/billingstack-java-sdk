package com.billingstack

import grails.converters.JSON

class PaymentGatewaysController {

		def paymentGatewaysService

		def list() {
			try {
				render paymentGatewaysService.list() as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def create() {
			try {
				render paymentGatewaysService.create(params.merchantId, request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def show(String merchantId, String paymentGatewayId) {
			try {
				render paymentGatewaysService.show(paymentGatewayId) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def update() {
			try {
				render paymentGatewaysService.update(request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String paymentGatewayId) {
			try {
				paymentGatewaysService.delete(paymentGatewayId)
				render(status : 204)
			} catch(e) {
				render onError(e) as JSON
			}
		}
		
		private onError(e) {
			log.error(e.message,e)
			response.status = 500
			["error":e.message]
		}

}
