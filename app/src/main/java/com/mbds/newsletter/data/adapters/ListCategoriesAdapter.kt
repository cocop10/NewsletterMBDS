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
import com.mbds.newsletter.fragments._CategoryFragment
import com.mbds.newsletter.models.Article
import com.mbds.newsletter.models.ArticleQuery
import com.mbds.newsletter.models.Category
import java.text.SimpleDateFormat
import java.util.*

class ListCategoriesAdapter(holder: _CategoryFragment) : RecyclerView.Adapter<ListCategoriesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.chip_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list: MutableList<String> = mutableListOf()

        enumValues<Category>().forEach { list += listOf(it.name) }

        val category: String = list[position]

        holder.mCategoryname.text = category
    }

    override fun getItemCount(): Int {
        return Category.values().size
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val mCategoryname: TextView

        init {
            // Enable click on item
            mCategoryname = view.findViewById(R.id.source_item)
        }
    }
}