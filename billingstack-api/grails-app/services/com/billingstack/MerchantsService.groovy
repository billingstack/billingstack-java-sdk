package com.billingstack

class MerchantsService {
	
	def map(merchant) {
		def entity = [
			id : merchant.id,
			//name : merchant.name,
			//title : merchant.title,
			language_id : merchant.language.name,
			currency_id : merchant.currency.name
		]
		if(merchant.metadata) {
			entity.metadata = [:]
			merchant.metadata.each {
				entity.metadata[it.key] = it.value
			}
		}
		entity
	}

	def list() {
		Merchant.list().collect { map(it) }
	}

	def create(entity) {
		def merchant = Merchant.newInstance(
			id : entity.id,
			name : entity.name,
			title : entity.title,
			language : Language.load(entity.language_id),
			currency : Currency.load(entity.currency_id)
		)
		merchant.metadata = entity.metadata.collect { k, v ->
			Metadata.newInstance(key : k, value : v)
		}
		map(merchant.save(failOnError : true, flush : true))
	}

	def show(String merchantId) {
		map(Merchant.get(merchantId))
	}

	def update(entity) {
		def merchant = Merchant.get(entity.id)
		map(merchant)
	}

	def delete(String merchantId) {
		Merchant.load(merchantId).delete(flush : true)
	}

}
