package com.mbds.newsletter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mbds.newsletter.NavigationListener
import com.mbds.newsletter.R
import com.mbds.newsletter.data.ArticleRepository
import com.mbds.newsletter.data.SourceRepository
import com.mbds.newsletter.data.adapters.ListArticlesAdapter
import com.mbds.newsletter.data.adapters.ListArticlesHandler
import com.mbds.newsletter.data.adapters.ListSourcesAdapter
import com.mbds.newsletter.data.adapters.ListSourcesHandler
import com.mbds.newsletter.models.ArticleQuery
import com.mbds.newsletter.models.SourceQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.xml.transform.Source

class MainFragment : Fragment() {
    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.articles_list)
            it.addChildFragment(_SourceFragment(), R.id.list_sources_fragment)
            it.addChildFragment(_CategoryFragment(), R.id.list_categories_fragment)
            it.addChildFragment(_CountryFragment(), R.id.list_countries_fragment)
        }
    }
}