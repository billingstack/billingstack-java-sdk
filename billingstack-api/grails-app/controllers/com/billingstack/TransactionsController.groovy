package com.billingstack

import grails.converters.JSON

class TransactionsController {

		def transactionsService

		def list(String merchantId) {
			try {
				render transactionsService.list(merchantId, params) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def create(String merchantId) {
			try {
				render transactionsService.create(merchantId, request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def show(String transactionId) {
			try {
				render transactionsService.show(transactionId) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def update() {
			try {
				render  transactionsService.update(request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String transactionId) {
			try {
				transactionsService.delete(transactionId)
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
