package com.mbds.newsletter.data

import com.mbds.newsletter.data.service.CategoryOnlineService
import com.mbds.newsletter.models.ArticleQuery
import com.mbds.newsletter.models.Category

class CategoryRepository {
    private val apiService: CategoryOnlineService

    init {
        apiService = CategoryOnlineService()
    }

    fun getArticles(category: Category): ArticleQuery = apiService.getArticles(category)

    companion object {
        private var instance: CategoryRepository? = null
        fun getInstance(): CategoryRepository {
            if (instance == null) {
                instance = CategoryRepository()
            }
            return instance!!
        }
    }
}