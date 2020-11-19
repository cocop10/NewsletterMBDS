package com.mbds.newsletter.data.adapters

import androidx.fragment.app.Fragment

interface ListArticlesHandler {
    fun getArticles(query: String, fragment: String)
}