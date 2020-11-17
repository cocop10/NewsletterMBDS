package com.mbds.newsletter.data.service

import com.mbds.newsletter.models.ArticleQuery
import com.mbds.newsletter.models.Category

interface CategoryService {
    fun getArticles(category: Category) : ArticleQuery
}