package com.mbds.newsletter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.NavigationListener
import com.mbds.newsletter.R
import com.mbds.newsletter.data.ArticleRepository
import com.mbds.newsletter.data.adapters.ListHeadlinesAdapter
import com.mbds.newsletter.data.adapters.ListHeadlinesHandler
import com.mbds.newsletter.models.Article
import com.mbds.newsletter.models.ArticleQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class _HeadlinesFragment: Fragment(), ListHeadlinesHandler {
    private lateinit var recyclerView: RecyclerView
    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_articles_fragment, container, false)
        recyclerView = view.findViewById(R.id.article_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArticles()
    }

    /**
     * Récupère la liste des articles dans un thread secondaire
     */
    override fun getArticles() {
        lifecycleScope.launch(Dispatchers.IO) {
            val articles = ArticleRepository.getInstance().getHeadlines()
            bindData(articles)
        }
    }

    override fun showDetails(article: Article) {
        (activity as? NavigationListener)?.let {
            it.showFragment(ArticleFragment(article))
        }
    }

    /**
     * Rempli le recyclerview avec les données récupérées dans le web service
     * Cette action doit s'effectuer sur le thread principale
     * Car on ne peut mas modifier les éléments de vue dans un thread secondaire
     */
    private fun bindData(articles: ArticleQuery) {
        lifecycleScope.launch(Dispatchers.Main) {
            val adapter = ListHeadlinesAdapter(articles, this@_HeadlinesFragment, requireContext())
            recyclerView.adapter = adapter
        }
    }
}