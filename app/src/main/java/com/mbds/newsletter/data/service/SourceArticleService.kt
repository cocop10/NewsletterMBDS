package com.mbds.newsletter.data.service

import com.mbds.newsletter.models.ArticleQuery
import com.mbds.newsletter.models.Source

interface SourceArticleService {
    fun getArticles(source: String) : ArticleQuery
}