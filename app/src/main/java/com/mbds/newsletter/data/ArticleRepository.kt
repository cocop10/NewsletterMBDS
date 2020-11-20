package com.mbds.newsletter.data

import com.mbds.newsletter.data.service.ArticleOnlineService
import com.mbds.newsletter.models.Article
import com.mbds.newsletter.models.ArticleQuery

class ArticleRepository {
    private val apiService: ArticleOnlineService

    init {
        apiService = ArticleOnlineService()
    }

    fun getHeadlines(): ArticleQuery = apiService.getHeadlines()
    fun getArticles(query: String): ArticleQuery = apiService.getArticles(query)
    fun getCategoryArticles(query: String): ArticleQuery = apiService.getCategoryArticles(query)
    fun getCountryArticles(query: String): ArticleQuery = apiService.getCountryArticles(query)
    fun getSourceArticles(query: String): ArticleQuery = apiService.getSourceArticles(query)

    companion object {
        private var instance: ArticleRepository? = null
        fun getInstance(): ArticleRepository {
            if (instance == null) {
                instance = ArticleRepository()
            }
            return instance!!
        }
    }
}