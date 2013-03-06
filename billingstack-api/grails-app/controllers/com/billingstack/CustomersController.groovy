package com.billingstack

import grails.converters.JSON

class CustomersController {

		def customersService

		def list() {
			try {
				render customersService.list() as JSON
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

		def show(String customerId) {
			try {
				render customersService.show(customerId) as JSON
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
		
		private onError(e) {
			log.error(e.message,e)
			response.status = 500
			["error":e.message]
		}

}
