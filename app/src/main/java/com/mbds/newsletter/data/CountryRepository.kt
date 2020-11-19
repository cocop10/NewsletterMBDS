package com.mbds.newsletter.data

import com.mbds.newsletter.data.service.CountryOnlineService
import com.mbds.newsletter.models.ArticleQuery
import com.mbds.newsletter.models.Country

class CountryRepository {
    private val apiService: CountryOnlineService

    init {
        apiService = CountryOnlineService()
    }

    fun getArticles(country: Country): ArticleQuery = apiService.getArticles(country)

    companion object {
        private var instance: CountryRepository? = null
        fun getInstance(): CountryRepository {
            if (instance == null) {
                instance = CountryRepository()
            }
            return instance!!
        }
    }
}