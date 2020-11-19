package com.mbds.newsletter.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mbds.newsletter.NavigationListener
import com.mbds.newsletter.R
import com.mbds.newsletter.fragments.ArticleListFragment
import com.mbds.newsletter.fragments._CategoryFragment
import com.mbds.newsletter.fragments._CountryFragment
import com.mbds.newsletter.models.Article
import com.mbds.newsletter.models.ArticleQuery
import com.mbds.newsletter.models.Category
import com.mbds.newsletter.models.Country
import java.text.SimpleDateFormat
import java.util.*

class ListCountriesAdapter(private val handler: ListSourcesHandler) : RecyclerView.Adapter<ListCountriesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.chip_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list: MutableList<String> = mutableListOf()

        enumValues<Country>().forEach { list += listOf(it.name) }

        val country: String = list[position]

        holder.mCountryname.text = country

        holder.mCountryname.setOnClickListener {
            handler.showArticles(holder.mCountryname.text as String)
        }
    }

    override fun getItemCount(): Int {
        return Country.values().size
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val mCountryname: TextView

        init {
            // Enable click on item
            mCountryname = view.findViewById(R.id.source_item)
        }
    }
}