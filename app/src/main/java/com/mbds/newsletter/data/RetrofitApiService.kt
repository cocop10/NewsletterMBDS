package com.mbds.newsletter.data

interface RetrofitApiService {
    //GET --> On lance une requête de type GET
    // everything est l'action du web service qu'on veut appeler
    // Elle sera concaténée avec l'url prédéfini dans retrofit
    @GET("/everything")
    fun list(): Call<List<Article>>
}