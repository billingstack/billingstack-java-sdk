package com.billingstack

import grails.converters.JSON

class UsageController {

		def usageService

		def list() {
			try {
				render usageService.list() as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def create(String merchantId, String customerId) {
			try {
				render usageService.create(merchantId, customerId, request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def show(String merchantId, String customerId, String usageId) {
			try {
				render usagesService.show(usageId) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def update() {
			try {
				render usagesService.update(request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String usageId) {
			try {
				usagesService.delete(usageId)
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
