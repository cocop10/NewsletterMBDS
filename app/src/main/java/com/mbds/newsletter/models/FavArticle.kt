package com.mbds.newsletter.models

public class FavArticle {

    private var key_id: String? = null
    private var article_title: String? = null
    private var article_description: String? = null
    private var article_author: String? = null
    private var article_image: String? = null


    fun FavArticle() {}

    fun FavArticle(
        key_id: String?,
        article_title: String?,
        article_description: String?,
        article_author: String?,
        article_image: String?
    ) {
        this.key_id = key_id
        this.article_title = article_title
        this.article_description = article_description
        this.article_author = article_author
        this.article_image = article_image
    }

    fun getKey_id(): String? {
        return key_id
    }

    fun setKey_id(key_id: String?) {
        this.key_id = key_id
    }

    fun getArticle_title(): String? {
        return article_title
    }

    fun setArticle_title(article_title: String?) {
        this.article_title = article_title
    }

    fun getArticle_description(): String? {
        return article_description
    }

    fun setArticle_description(article_description: String?) {
        this.article_description = article_description
    }

    fun getArticle_author(): String? {
        return article_author
    }

    fun setArticle_author(article_author: String?) {
        this.article_author = article_author
    }

    fun getArticle_image(): String? {
        return article_image
    }

    fun setArticle_image(article_image: String?) {
        this.article_image = article_image
    }

}