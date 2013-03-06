package com.billingstack

import grails.converters.JSON

class PaymentGatewayProvidersController {

		def paymentGatewayProvidersService

		def list() {
			try {
				render paymentGatewayProvidersService.list() as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def create() {
			try {
				render paymentGatewayProvidersService.create(request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def show(String paymentGatewayProviderId) {
			try {
				render paymentGatewayProvidersService.show(paymentGatewayProviderId) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def update() {
			try {
				render paymentGatewayProvidersService.update(request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String paymentGatewayProviderId) {
			try {
				paymentGatewayProvidersService.delete(paymentGatewayProviderId)
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
