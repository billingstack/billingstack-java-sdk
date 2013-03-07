package com.billingstack

import grails.converters.JSON

class UsersController {

		def usersService

		def list(String merchantId, String customerId) {
			try {
				def filters = [:]
				render usersService.list(merchantId, customerId, filters) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def create() {
			try {
				render usersService.create(request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def show(String merchantId, String customerId, String userId) {
			try {
				render usersService.show(userId) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def update() {
			try {
				render usersService.update(request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String userId) {
			try {
				usersService.delete(userId)
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
