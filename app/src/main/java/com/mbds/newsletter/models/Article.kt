package com.mbds.newsletter.models

import java.util.*

data class Article (
    var id: String,
    val title: String,
    val description: String,
    val author: String,
    val publishedAt: Date,
    val urlToImage: String,
    val content: String,
    var favorite: String = "0"
)