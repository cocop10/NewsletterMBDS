package com.mbds.newsletter.models

data class ArticleQuery (
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)