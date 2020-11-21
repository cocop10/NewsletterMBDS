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
import com.mbds.newsletter.data.adapters.ArticleAdapter
import com.mbds.newsletter.data.adapters.ArticleHandler
import com.mbds.newsletter.models.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleFragment(article: Article) : Fragment(), ArticleHandler {
    private lateinit var recyclerView: RecyclerView
    private val article = article

    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detailed_article_fragment, container, false)
        recyclerView = view.findViewById(R.id.article_item)
        recyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindData(article)
        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.article)
        }
    }

    /**
     * Rempli le recyclerview avec les données récupérées dans le web service
     * Cette action doit s'effectuer sur le thread principale
     * Car on ne peut mas modifier les éléments de vue dans un thread secondaire
     */
    private fun bindData(article: Article) {
        lifecycleScope.launch(Dispatchers.Main) {
            val adapter = ArticleAdapter(article, this@ArticleFragment, requireContext())
            recyclerView.adapter = adapter
        }
    }
}