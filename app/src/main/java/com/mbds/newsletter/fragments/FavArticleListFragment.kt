package com.mbds.newsletter.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.FavDB
import com.mbds.newsletter.NavigationListener
import com.mbds.newsletter.R
import com.mbds.newsletter.data.adapters.ListFavArticlesAdapter
import com.mbds.newsletter.models.FavArticle
import java.util.*

class FavArticleListFragment: Fragment()  {
    private lateinit var recyclerView: RecyclerView

    private lateinit var favDB: FavDB
    private var favArticleList: MutableList<FavArticle> = ArrayList<FavArticle>()
    private lateinit var favAdapter: ListFavArticlesAdapter
    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_favorite_fragment, container, false)


        recyclerView = view.findViewById(R.id.article_fav_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        favDB = FavDB(activity)
        loadData(requireContext())
        return view
    }

    private fun loadData(context: Context) {


        if (favArticleList != null) {
            favArticleList.clear()
        }
        val db = favDB.readableDatabase
        val cursor = favDB.select_all_favorite_list()
        try {
            while (cursor.moveToNext()) {
                val title = cursor.getString(cursor.getColumnIndex(FavDB.ARTICLE_TITLE))
                val description = cursor.getString(cursor.getColumnIndex(FavDB.ARTICLE_DESCRIPTION))
                val author = cursor.getString(cursor.getColumnIndex(FavDB.ARTICLE_AUTHOR))
                val id = cursor.getString(cursor.getColumnIndex(FavDB.KEY_ID))
                val urlToImage = cursor.getString(cursor.getColumnIndex(FavDB.ARTICLE_IMAGE))
                val favArticle = FavArticle(id, title, description, author, urlToImage)
                favArticleList.add(favArticle)
            }
        } finally {
            if (cursor != null && cursor.isClosed) cursor.close()
            db.close()
        }
        favAdapter = ListFavArticlesAdapter(context, favArticleList)
        recyclerView.adapter = favAdapter
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.article_favorite_list)
        }
    }
}