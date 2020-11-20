package com.mbds.newsletter.models


import java.util.*


data class Article (
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val author: String,
    val publishedAt: Date,
    val urlToImage: String,
    val content: String,
    var favorite: String = "0"
)