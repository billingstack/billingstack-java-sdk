package com.billingstack

import grails.converters.JSON

class InvoiceLinesController {

		def invoiceLinesService

		def list(String merchantId, String invoiceId) {
			try {
				render invoiceLinesService.list() as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def create(String merchantId, String invoiceId) {
			try {
				render invoiceLinesService.create(invoiceId, request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def show(String merchantId, String invoiceId, String invoiceLineId) {
			try {
				render invoiceLinesService.show(invoiceLineId) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def update(String merchantId, String invoiceId, String invoiceLineId) {
			try {
				render invoiceLinesService.update(merchantId, invoiceId, invoiceLineId, request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String merchantId, String invoiceId, String invoiceLineId) {
			try {
				invoiceLinesService.delete(invoiceLineId)
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
