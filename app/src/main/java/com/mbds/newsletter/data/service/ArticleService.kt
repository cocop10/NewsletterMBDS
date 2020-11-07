package com.mbds.newsletter.data.service

import com.mbds.newsletter.models.Article

interface ArticleService {
    fun getArticles(): List<Article>
}