package com.billingstack

class ProductsService {
	
	def map(product) {
		[
			id : product.id,
			merchant_id : product.merchant.id,
			provider : product.provider,
			source : product.source,
			name : product.name,
			title : product.title,
			description : product.description
		]
	}

	def list() {
		Product.list().collect { map(it) }
	}

	def create(merchantId, entity) {
		Product product = Product.newInstance(
			merchant : Merchant.load(merchantId),
			provider : entity.provider,
			source : entity.source,
			name : entity.name,
			title : entity.title,
			description : entity.description
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
