package com.mbds.newsletter.data.service

import com.mbds.newsletter.models.Article
import com.mbds.newsletter.models.ArticleQuery
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApiService {
    //GET --> On lance une requête de type GET
    // everything est l'action du web service qu'on veut appeler
    // Elle sera concaténée avec l'url prédéfini dans retrofit
    @GET("/everything")
    fun list(): Call<ArticleQuery>
}