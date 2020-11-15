package com.mbds.newsletter.data

import com.mbds.newsletter.data.service.ArticleOnlineService
import com.mbds.newsletter.models.Article

class ArticleRepository {
    private val apiService: ArticleOnlineService

    init {
        apiService = ArticleOnlineService()
    }

    fun getArticles(): List<Article> = apiService.getArticles()

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