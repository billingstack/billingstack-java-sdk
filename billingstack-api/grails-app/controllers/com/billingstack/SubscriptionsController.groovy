package com.billingstack

import grails.converters.JSON

class SubscriptionsController {

		def subscriptionsService

		def list() {
			try {
				render subscriptionsService.list() as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def create(String merchantId, String customerId) {
			try {
				render subscriptionsService.create(merchantId, customerId, request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def show(String merchantId, String customerId, String subscriptionId) {
			try {
				render subscriptionsService.show(subscriptionId) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def update(String merchantId, String customerId, String subscriptionId) {
			try {
				render subscriptionsService.update(merchantId, customerId, subscriptionId, request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String merchantId, String customerId, String subscriptionId) {
			try {
				subscriptionsService.delete(subscriptionId)
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
