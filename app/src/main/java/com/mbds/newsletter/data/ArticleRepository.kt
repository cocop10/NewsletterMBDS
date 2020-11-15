package com.mbds.newsletter.data

import com.mbds.newsletter.data.service.ArticleOnlineService
import com.mbds.newsletter.models.Article
import com.mbds.newsletter.models.ArticleQuery

class ArticleRepository {
    private val apiService: ArticleOnlineService

    init {
        apiService = ArticleOnlineService()
    }

    fun getArticles(): ArticleQuery = apiService.getArticles()

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