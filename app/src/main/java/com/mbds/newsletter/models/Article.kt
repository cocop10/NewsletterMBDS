package com.mbds.newsletter.models

data class Article (
    val title: String,
    val description: String,
    val author: String,
    val date: String,
    val imgUrl: String,
    val favorite: Boolean
)