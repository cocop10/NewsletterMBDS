package com.mbds.newsletter.data.adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mbds.newsletter.R
import com.mbds.newsletter.models.Article
import com.mbds.newsletter.models.ArticleQuery
import java.text.SimpleDateFormat
import java.util.*

class ArticleAdapter(
    item: Article, private val handler: ArticleHandler
) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    private val mArticle: Article = item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.detailed_article_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article: Article = mArticle
        val context = holder.itemView.context

        //Conversion de la date
        val sdfOut = SimpleDateFormat("dd-MM-yyyy")
        val date: Date = article.publishedAt
        val dateString = sdfOut.format(date)

        // Display Neighbour Name
        if (article.author.isNullOrBlank()) holder.mArticleAuthor.visibility = GONE else holder.mArticleAuthor.text = article.author
        holder.mArticleName.text = article.title
        holder.mArticleDate.text = dateString
        holder.mArticleContent.text = article.content

        Glide.with(context)
            .load(article.urlToImage)
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_image_24)
            .skipMemoryCache(false)
            .into(holder.mArticleAvatar)

        if (!article.favorite) holder.mFavoriteButton.setImageResource(R.drawable.ic_baseline_favorite_border_24) else holder.mFavoriteButton.setImageResource(
            R.drawable.ic_baseline_favorite_24
        )
    }

    override fun getItemCount(): Int {
        return 1
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val mArticleAvatar: ImageView
        val mArticleName: TextView
        val mArticleAuthor: TextView
        val mArticleDate: TextView
        val mArticleContent: TextView
        val mFavoriteButton: ImageView
        init {
            // Enable click on item
            mArticleAvatar = view.findViewById(R.id.article_urlToImage)
            mArticleName = view.findViewById(R.id.article_title)
            mArticleAuthor = view.findViewById(R.id.article_author)
            mArticleDate = view.findViewById(R.id.article_publishedAt)
            mArticleContent = view.findViewById(R.id.article_content)
            mFavoriteButton = view.findViewById(R.id.article_favorite)
        }
    }
}