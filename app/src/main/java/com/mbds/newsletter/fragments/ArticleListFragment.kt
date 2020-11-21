package com.mbds.newsletter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mbds.newsletter.NavigationListener
import com.mbds.newsletter.R
import com.mbds.newsletter.data.ArticleRepository
import com.mbds.newsletter.data.adapters.ListArticlesAdapter
import com.mbds.newsletter.data.adapters.ListArticlesHandler
import com.mbds.newsletter.models.Article
import com.mbds.newsletter.models.ArticleQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleListFragment(query: String, nameFragment: String) : Fragment(), ListArticlesHandler {
    private lateinit var recyclerView: RecyclerView
    private val query = query
    private val nameFragment = nameFragment
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
        // Set the color
        view.setBackgroundColor(getResources().getColor(android.R.color.white));
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArticles(this.query, this.nameFragment)

        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.articles_list)
        }
    }

    /**
     * Récupère la liste des articles dans un thread secondaire
     */
    override fun getArticles(subject: String, fragment: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val articles: ArticleQuery
                    when (fragment) {
                        "_ArticleFragment" -> articles = ArticleRepository.getInstance().getArticles(subject)
                        "_CategoryFragment" -> articles = ArticleRepository.getInstance().getCategoryArticles(subject)
                        "_CountryFragment" -> articles = ArticleRepository.getInstance().getCountryArticles(subject)
                        "_SourceFragment" -> articles = ArticleRepository.getInstance().getSourceArticles(subject)
                        else -> {
                            articles = ArticleRepository.getInstance().getCountryArticles(subject)
                        }
                    }
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
            val adapter = ListArticlesAdapter(articles, this@ArticleListFragment, requireContext())
            recyclerView.adapter = adapter
        }
    }
}