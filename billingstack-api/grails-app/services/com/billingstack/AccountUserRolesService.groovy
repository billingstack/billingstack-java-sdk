package com.billingstack

class AccountUserRolesService {
	
	def usersService
	
	def map(accountUserRole) {
		[
			user : accountUserRole.user.id,
			account : accountUserRole.account.id,
			role : accountUserRole.role.id
		]
	}
	
	def listUsers(String accountId, filters) {
		def list = AccountUserRole.createCriteria().list() {
			projections {
				distinct "user"
			}
			eq "account.id", accountId
		}
		list.collect { usersService.map(it) }
	}

	def create(String accountId, String userId, String roleId) {
		def userAccountRole = AccountUserRole.newInstance(
			user : User.load(userId),
			account : Account.load(accountId),
			role : Role.load(roleId)
		)
		map(userAccountRole.save(flush : true, failOnError : true))
	}

	def delete(String accountId, String userId, String roleId) {
		def userRole = UserAccountRole.findByUserAndRole(
			user : User.load(userId),
			account : Account.load(accountId),
			role : Role.load(roleId)
		).delete(flush : true)
	}

}
