package com.billingstack

class LanguagesService {

	def map(language) {
		[
			name : language.name,
			title : language.title
		]
	}

	def list() {
		Language.list().collect { map(it) }
	}

	def create(entity) {
		def language = Language.newInstance(
			name : entity.name,
			title : entity.title
		)
		map(language.save(failOnError : true))
	}

	def show(String languageName) {
		map(Language.get(languageName))
	}

	def update(entity) {
		def language = Language.get(entity.id)
		map(language)
	}

	def delete(String languageName) {
		Language.load(languageName).delete(flush : true)
	}

}
