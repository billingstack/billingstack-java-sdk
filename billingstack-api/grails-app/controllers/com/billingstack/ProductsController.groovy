package com.billingstack

import grails.converters.JSON

class ProductsController {

		def productsService

		def list() {
			try {
				render productsService.list() as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def create(String merchantId) {
			try {
				render productsService.create(merchantId, request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def show(String merchantId, String productId) {
			try {
				render productsService.show(productId) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def update(String merchantId, String productId) {
			try {
				render productsService.update(merchantId, productId, request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String merchantId, String productId) {
			try {
				productsService.delete(productId)
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
