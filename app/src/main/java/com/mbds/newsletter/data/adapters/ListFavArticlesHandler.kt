package com.mbds.newsletter.data.adapters

import com.mbds.newsletter.models.Article

interface ListFavArticlesHandler {
    fun showDetails(article: Article)
}