package com.mbds.newsletter.data.service

import android.content.res.Resources
import com.mbds.newsletter.models.ArticleQuery
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class ArticleOnlineService : ArticleService {
    private val service: RetrofitApiService

    init {
        val retrofit = buildClient()
        //Init the api service with retrofit
        service = retrofit.create(RetrofitApiService::class.java)
    }

    /**
     * Configure retrofit
     */
    private fun buildClient(): Retrofit {
        val httpClient = OkHttpClient.Builder().apply {
            addLogInterceptor(this)
            addApiInterceptor(this)
        }.build()
        return Retrofit
            .Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    /**
     * Add a logger to the client so that we log every request
     */
    private fun addLogInterceptor(builder: OkHttpClient.Builder) {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        builder.addNetworkInterceptor(httpLoggingInterceptor)
    }

    /**
     * Intercept every request to the API and automatically add
     * the api key as query parameter
     */
    private fun addApiInterceptor(builder: OkHttpClient.Builder) {
        builder.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val originalHttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apiKey", apiKey)
                    .build()

                val requestBuilder = original.newBuilder()
                    .url(url)
                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        })
    }

    override fun getArticles(query: String): ArticleQuery {
        val query = service.list(query).execute().body()
        return query!!
    }

    override fun getCategoryArticles(query: String): ArticleQuery {
        val query = service.category(query).execute().body()
        return query!!
    }

    override fun getCountryArticles(query: String): ArticleQuery {
        val query = service.country(query).execute().body()
        return query!!
    }

    override fun getSourceArticles(query: String): ArticleQuery {
        val query = service.source(query).execute().body()
        return query!!
    }

    override fun getHeadlines(): ArticleQuery {
        val query = service.country(Locale.getDefault().getCountry()).execute().body() //Affiche les articles à la une selon la langue de l'appareil
        return query!!
    }

    companion object {
        private const val apiKey = "3619955908054248a433678895e7c6e4"
        private const val apiUrl = "https://newsapi.org/"
    }
}