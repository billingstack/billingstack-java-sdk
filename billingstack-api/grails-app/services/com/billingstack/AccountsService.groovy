package com.billingstack

class AccountsService {

	def map(account) {
		def entity = [
			id : account.id,
			name : account.name,
			title : account.title
		]
		if(account.metadata) {
			entity.metadata = [:]
			account.metadata.each {
				entity.metadata[it.key] = it.value
			}
		}
		entity
	}

	def list() {
		Account.list().collect { map(it) }
	}

	def create(entity) {
		def account = Account.newInstance(
			name : entity.name,
			title : entity.title
		)
		account.metadata = entity.metadata.collect { k, v ->
			Metadata.newInstance(key : k, value : v)
		}
		map(account.save(failOnError : true, flush : true))
	}

	def show(String accountId) {
		map(Account.get(accountId))
	}

	def update(entity) {
		def account = Account.get(entity.id)
		map(account)
	}

	def delete(String accountId) {
		Account.load(accountId).delete(flush : true)
	}

}
