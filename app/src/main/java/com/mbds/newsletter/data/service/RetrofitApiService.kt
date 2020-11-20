package com.mbds.newsletter.data.service

import com.mbds.newsletter.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiService {
    //GET --> On lance une requête de type GET
    // everything est l'action du web service qu'on veut appeler
    // Elle sera concaténée avec l'url prédéfini dans retrofit
    @GET("/v2/everything")
    fun list(@Query("q") query: String): Call<ArticleQuery>

    @GET("/v2/sources")
    fun sources(): Call<SourceQuery>

    @GET("/v2/top-headlines")
    fun source(@Query("sources") source: String): Call<ArticleQuery>

    @GET("/v2/top-headlines")
    fun category(@Query("category") category: Category): Call<ArticleQuery>

    @GET("/v2/top-headlines")
    fun country(@Query("country") country: Country): Call<ArticleQuery>

}