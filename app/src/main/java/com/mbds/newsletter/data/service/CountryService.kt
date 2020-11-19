package com.mbds.newsletter.data.service

import com.mbds.newsletter.models.ArticleQuery
import com.mbds.newsletter.models.Country

interface CountryService {
    fun getArticles(country: Country) : ArticleQuery
}