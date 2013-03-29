package com.billingstack

import grails.converters.JSON

class UsageController {

		def usageService

		def list(String merchantId) {
			try {
				render usageService.list(merchantId, params) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def create(String merchantId) {
			try {
				render usageService.create(merchantId, request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def show(String merchantId, String usageId) {
			try {
				render usagesService.show(usageId) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def update(String merchantId, String usageId) {
			try {
				render usagesService.update(request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String merchantId, String usageId) {
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
