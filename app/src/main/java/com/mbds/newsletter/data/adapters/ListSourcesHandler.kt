package com.mbds.newsletter.data.adapters

interface ListSourcesHandler {
    fun getSources()
    fun showArticles(query: String)
}