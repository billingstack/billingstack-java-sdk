package com.billingstack

import grails.converters.JSON

class PlansController {

		def plansService

		def list(String merchantId) {
			try {
				render plansService.list(merchantId) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def create() {
			try {
				render plansService.create(params.merchantId, request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def show(String planId) {
			try {
				render plansService.show(planId) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def update(String planId) {
			try {
				render plansService.update(planId, request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String planId) {
			try {
				plansService.delete(planId)
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
