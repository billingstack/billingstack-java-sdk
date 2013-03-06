package com.billingstack

import grails.converters.JSON

class CustomerPaymentMethodsController {

		def customerPaymentMethodsService

		def list() {
			try {
				render customerPaymentMethodsService.list() as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def create() {
			try {
				render customerPaymentMethodsService.create(params.customerId, request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def show(String paymentMethodId) {
			try {
				render customerPaymentMethodsService.show(paymentMethodId) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def update() {
			try {
				render customerPaymentMethodsService.update(request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String paymentMethodId) {
			try {
				customerPaymentMethodsService.delete(paymentMethodId)
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
