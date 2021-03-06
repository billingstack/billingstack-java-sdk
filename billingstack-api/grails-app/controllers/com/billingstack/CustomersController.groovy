package com.billingstack

import grails.converters.JSON

class CustomersController {

		def customersService

		def list(String merchantId) {
			try {
				render customersService.list(merchantId) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def create() {
			try {
				render customersService.create(params.merchantId, request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def show(String merchantId, String customerId) {
			try {
				render customersService.show(merchantId, customerId) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def update() {
			try {
				render customersService.update(request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String customerId) {
			try {
				customersService.delete(customerId)
				render(status : 204)
			} catch(e) {
				render onError(e) as JSON
			}
		}
		
		def action(String merchantId, String customerId) {
			try {
				customersService.action(merchantId, customerId, request.JSON)
				render(status : 204)
			} catch(e) {
				render onError(e) as JSON
			}
		}
		
		private onError(e) {
			log.error(e.message,e)
			if("not.found".equals(e.message)) {
				response.status = 404
			} else {
				response.status = 500
			}
			["error":e.message]
		}

}
