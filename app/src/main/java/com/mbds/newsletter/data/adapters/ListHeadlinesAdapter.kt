package com.mbds.newsletter.data.adapters

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mbds.newsletter.FavDB
import com.mbds.newsletter.R
import com.mbds.newsletter.models.Article
import com.mbds.newsletter.models.ArticleQuery
import java.text.SimpleDateFormat
import java.util.*

class ListHeadlinesAdapter(
    items: ArticleQuery, private val handler: ListHeadlinesHandler, private val context: Context
) : RecyclerView.Adapter<ListHeadlinesAdapter.ViewHolder>() {
    private val mArticles: ArticleQuery = items
    private lateinit var favDB: FavDB

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        favDB = FavDB(context);
        //create table on first
        val prefs: SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val firstStart = prefs.getBoolean("firstStart", true)
        if (firstStart) {
            createTableOnFirstStart()
        }

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article: Article = mArticles.articles[position]
        val context = holder.itemView.context

        //generate ID à partir de la date
        val sdfPattern = SimpleDateFormat("yyMMddHHmmssSSS")
        val dateId: Date = article.publishedAt
        val idString = sdfPattern.format(dateId)
        article.id = idString

        readCursorData(article, holder)

        //Conversion de la date
        val sdfOut = SimpleDateFormat("dd-MM-yyyy")
        val date: Date = article.publishedAt
        val dateString = sdfOut.format(date)

        // Display Neighbour Name
        holder.mArticleName.text = article.title
        holder.mArticleDescription.text = article.description
        holder.mArticleAuthor.text = article.author
        holder.mArticleDate.text = dateString
        holder.mArticleURL.text = article.url

        Glide.with(context)
            .load(article.urlToImage)
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_image_24)
            .skipMemoryCache(false)
            .into(holder.mArticleAvatar)

        holder.mBackground.setOnClickListener {
            handler.showDetails(article)
        }

        // Test des valeurs null
        val title = if (article.title != null) article.title else ""
        val description = if (article.description != null) article.description else ""
        val author = if (article.author != null) article.author else ""
        val urlToImage = if (article.urlToImage != null) article.urlToImage else ""
        val url = if (article.url != null) article.url else ""

        // Check si article dans la bd fav
        // Initialisation button fav
        if (getCheck(idString)) holder.mFavoriteButton.setImageResource(R.drawable.ic_favorite_red_24dp)
        else holder.mFavoriteButton.setImageResource(
            R.drawable.ic_baseline_favorite_border_24
        )


        //add to fav btn
        holder.mFavoriteButton.setOnClickListener{
            if (!article.favorite) {
                article.favorite = true
                favDB.insertIntoTheDatabase(
                    article.id,
                    title,
                    description,
                    author,
                    urlToImage,
                    url,
                    article.favorite
                )
                holder.mFavoriteButton.setImageResource(R.drawable.ic_favorite_red_24dp)
            } else {
                article.favorite = false
                favDB.remove_fav(article.id)
                holder.mFavoriteButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }
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
        val mArticleURL: TextView
        val mFavoriteButton: ImageView
        val mBackground: ConstraintLayout

        init {
            // Enable click on item
            mArticleAvatar = view.findViewById(R.id.item_list_avatar)
            mArticleName = view.findViewById(R.id.item_list_name)
            mArticleDescription = view.findViewById(R.id.item_list_description)
            mArticleAuthor = view.findViewById(R.id.item_list_author)
            mArticleDate = view.findViewById(R.id.item_list_date)
            mArticleURL = view.findViewById(R.id.article_url)
            mFavoriteButton = view.findViewById(R.id.item_list_favorite_button)
            mBackground = view.findViewById(R.id.background)
        }
    }

    private fun getCheck(idString: String): Boolean {
        val favIdList: MutableList<String> = ArrayList()
        val db = favDB.readableDatabase
        val cursor = favDB.select_all_favorite_list()
        try {
            while (cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndex(FavDB.KEY_ID))
                favIdList.add(id)
            }
        } finally {
            if (cursor != null && cursor.isClosed) cursor.close()
            db.close()
        }
        return favIdList.contains(idString)
    }

    private fun createTableOnFirstStart() {
        val prefs =
            context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("firstStart", false)
        editor.apply()
    }

    private fun readCursorData(
        article: Article,
        viewHolder: ViewHolder
    ) {
        val cursor = favDB.read_all_data(article.id)
        val db = favDB.readableDatabase
        try {
            while (cursor.moveToNext()) {
                val item_fav_status =
                    cursor.getString(cursor.getColumnIndex(FavDB.FAVORITE_STATUS))
                article.favorite = item_fav_status.toBoolean()

                //check fav status
                if (item_fav_status != null && item_fav_status == "1") {
                    viewHolder.mFavoriteButton.setImageResource(R.drawable.ic_favorite_red_24dp)
                } else if (item_fav_status != null && item_fav_status == "0") {
                    viewHolder.mFavoriteButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed) cursor.close()
            db.close()
        }
    }
}