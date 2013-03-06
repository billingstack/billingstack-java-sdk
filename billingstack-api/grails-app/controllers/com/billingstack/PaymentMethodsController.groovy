package com.billingstack

import grails.converters.JSON

class PaymentMethodsController {

		def paymentMethodsService

		def list() {
			try {
				render paymentMethodsService.list() as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def create(String paymentGatewayProviderId) {
			try {
				render paymentMethodsService.create(paymentGatewayProviderId, request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def show(String paymentMethodId) {
			try {
				render paymentMethodsService.show(paymentMethodId) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def update() {
			try {
				render paymentMethodsService.update(request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String paymentMethodId) {
			try {
				paymentMethodsService.delete(paymentMethodId)
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
