package com.mbds.newsletter.models

data class Source (
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: Category,
    val language: String,
    val country: Country
)