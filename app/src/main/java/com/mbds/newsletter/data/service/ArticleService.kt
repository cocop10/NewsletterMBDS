package com.mbds.newsletter.data.service

import com.mbds.newsletter.models.ArticleQuery

interface ArticleService {
    fun getArticles(query: String): ArticleQuery
    fun getCategoryArticles(query: String): ArticleQuery
    fun getCountryArticles(query: String): ArticleQuery
    fun getSourceArticles(query: String): ArticleQuery
    fun getHeadlines(): ArticleQuery
}