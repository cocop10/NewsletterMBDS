package com.mbds.newsletter.data.adapters

import com.mbds.newsletter.models.Article

interface ListArticlesHandler {
    fun getArticles(query: String, fragment: String)
    fun showDetails(article: Article)
}