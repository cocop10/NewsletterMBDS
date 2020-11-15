package com.mbds.newsletter.data.service

import com.mbds.newsletter.models.ArticleQuery

interface ArticleService {
    fun getArticles(): ArticleQuery
}