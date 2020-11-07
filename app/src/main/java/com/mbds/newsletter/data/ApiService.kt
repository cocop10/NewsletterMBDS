package com.mbds.newsletter.data

interface ApiService {
    fun getArticles(): List<Article>
}