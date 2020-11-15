package com.mbds.newsletter.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mbds.newsletter.R
import com.mbds.newsletter.fragments.ArticleListFragment
import com.mbds.newsletter.models.Article
import com.mbds.newsletter.models.ArticleQuery
import java.text.SimpleDateFormat
import java.util.*

class ListArticlesAdapter(
    items: ArticleQuery, private val handler: ArticleListFragment
) : RecyclerView.Adapter<ListArticlesAdapter.ViewHolder>() {
    private val mArticles: ArticleQuery = items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article: Article = mArticles.articles[position]
        val context = holder.itemView.context

        //Conversion de la date
        val sdfOut = SimpleDateFormat("dd-MM-yyyy")
        val date: Date = article.publishedAt
        val dateString = sdfOut.format(date)

        // Display Neighbour Name
        holder.mArticleName.text = article.title
        holder.mArticleDescription.text = article.description
        holder.mArticleAuthor.text = article.author
        holder.mArticleDate.text = dateString

        Glide.with(context)
            .load(article.urlToImage)
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_image_24)
            .skipMemoryCache(false)
            .into(holder.mArticleAvatar)

        holder.mFavoriteButton.setOnClickListener {

        }

        if (!article.favorite) holder.mFavoriteButton.setImageResource(R.drawable.ic_baseline_favorite_border_24) else holder.mFavoriteButton.setImageResource(
            R.drawable.ic_baseline_favorite_24
        )
    }

    override fun getItemCount(): Int {
        return mArticles.articles.size
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val mArticleAvatar: ImageView
        val mArticleName: TextView
        val mArticleDescription: TextView
        val mArticleAuthor: TextView
        val mArticleDate: TextView
        val mFavoriteButton: ImageButton

        init {
            // Enable click on item
            mArticleAvatar = view.findViewById(R.id.item_list_avatar)
            mArticleName = view.findViewById(R.id.item_list_name)
            mArticleDescription = view.findViewById(R.id.item_list_description)
            mArticleAuthor = view.findViewById(R.id.item_list_author)
            mArticleDate = view.findViewById(R.id.item_list_date)
            mFavoriteButton = view.findViewById(R.id.item_list_favorite_button)
        }
    }
}