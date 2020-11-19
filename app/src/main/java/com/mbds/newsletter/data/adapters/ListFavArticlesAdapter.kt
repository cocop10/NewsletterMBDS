package com.mbds.newsletter.data.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mbds.newsletter.FavDB
import com.mbds.newsletter.R
import com.mbds.newsletter.models.FavArticle

class ListFavArticlesAdapter(
    private val context: Context, private val favArticleList: List<FavArticle>
) : RecyclerView.Adapter<ListFavArticlesAdapter.ViewHolder>() {

    private lateinit var favDB: FavDB

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.favorite_article_item,
            parent, false
        )
        favDB = FavDB(context);
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favArticle: FavArticle = favArticleList[position]
        val context = holder.itemView.context


        holder.mFavArticleName.text = favArticle.title
        holder.mFavArticleAuthor.text = favArticle.author
        holder.mFavArticleDescription.text = favArticle.description


        //Conversion de la date
        //val sdfOut = SimpleDateFormat("dd-MM-yyyy")
        //val date: Date = article.publishedAt
        //val dateString = sdfOut.format(date)


        Glide.with(context)
            .load(favArticle.urlToImage)
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_image_24)
            .skipMemoryCache(false)
            .into(holder.mFavArticleAvatar)

        //remove from fav after click

        //remove from fav after click
        holder.mFavFavoriteButton.setOnClickListener(View.OnClickListener {
            favDB.remove_fav(favArticle.id)
            removeItem(position)
        })




    }



    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val mFavArticleAvatar: ImageView
        val mFavArticleName: TextView
        val mFavArticleDescription: TextView
        val mFavArticleAuthor: TextView
        val mFavFavoriteButton: ImageButton

        init {
            // Enable click on item
            mFavArticleAvatar = view.findViewById(R.id.item_list_avatar)
            mFavArticleName = view.findViewById(R.id.item_list_name)
            mFavArticleDescription = view.findViewById(R.id.item_list_description)
            mFavArticleAuthor = view.findViewById(R.id.item_list_author)
            mFavFavoriteButton = view.findViewById(R.id.item_list_favorite_button)
        }
    }

    private fun removeItem(position: Int) {
        favArticleList.drop(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, favArticleList.size)
    }

    override fun getItemCount(): Int {
        return favArticleList.size
    }


}