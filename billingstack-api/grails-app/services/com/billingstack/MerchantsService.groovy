package com.billingstack

class MerchantsService {
	
	def map(merchant) {
		def entity = [
			id : merchant.id,
			name : merchant.name,
			title : merchant.title,
			language_id : merchant.language.id,
			currency_id : merchant.currency.id
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
			name : entity.name,
			title : entity.title,
			language : Language.findByIdOrName(entity.language_id, entity.language_id),
			currency : Currency.findByIdOrName(entity.currency_id, entity.currency_id)
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
