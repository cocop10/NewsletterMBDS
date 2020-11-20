package com.mbds.newsletter.data.adapters

import com.mbds.newsletter.models.Article

interface ListHeadlinesHandler {
    fun getArticles()
    fun showDetails(article: Article)
}