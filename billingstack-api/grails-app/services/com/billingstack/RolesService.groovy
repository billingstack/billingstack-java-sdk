package com.billingstack

class RolesService {
	
	def map(role) {
		[
			id : role.id,
			name : role.name
		]
	}

	def list() {
		Role.list().collect { map(it) }
	}

	def create(entity) {
		def role = Role.newInstance(
			name : entity.name
		)
		map(role.save())
	}

	def show(String roleId) {
		map(Role.get(roleId))
	}

	def update(entity) {
		def role = Role.get(entity.id)
		map(role)
	}

	def delete(String roleId) {
		Role.load(roleId).delete(flush : true)
	}

}
