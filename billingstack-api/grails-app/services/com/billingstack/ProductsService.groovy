package com.billingstack

import grails.converters.JSON

class ProductsService {
	
	def map(product) {
		[
			id : product.id,
			merchant_id : product.merchant.id,
			name : product.name,
			title : product.title,
			description : product.description,
			properties : JSON.parse(product.metadataJson)
		]
	}

	def list() {
		Product.list().collect { map(it) }
	}

	def create(merchantId, entity) {
		Product product = Product.newInstance(
			merchant : Merchant.load(merchantId),
			name : entity.name,
			title : entity.title,
			description : entity.description,
			metadataJson : (entity["properties"] as JSON).toString()
		)
		map(product.save(failOnError : true))
	}

	def show(String productId) {
		map(Product.get(productId))
	}

	def update(entity) {
		def product = Product.get(entity.id)
		map(product)
	}

	def delete(String productId) {
		Product.load(productId).delete(flush : true)
	}

}
