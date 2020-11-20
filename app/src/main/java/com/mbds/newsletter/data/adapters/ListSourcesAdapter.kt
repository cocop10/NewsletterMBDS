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
import com.mbds.newsletter.fragments.MainFragment
import com.mbds.newsletter.fragments._SourceFragment
import com.mbds.newsletter.models.Article
import com.mbds.newsletter.models.Source
import com.mbds.newsletter.models.SourceQuery
import java.text.SimpleDateFormat
import java.util.*

class ListSourcesAdapter(
    items: SourceQuery, private val handler: ListSourcesHandler
) : RecyclerView.Adapter<ListSourcesAdapter.ViewHolder>() {
    private val mSources: SourceQuery = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.chip_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val source: Source = mSources.sources[position]

        holder.mSourcename.text = source.id

        holder.mSourcename.setOnClickListener {
            handler.showArticles(holder.mSourcename.text as String)
        }
    }

    override fun getItemCount(): Int {
        return mSources.sources.size
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val mSourcename: TextView

        init {
            // Enable click on item
            mSourcename = view.findViewById(R.id.source_item)
        }
    }
}