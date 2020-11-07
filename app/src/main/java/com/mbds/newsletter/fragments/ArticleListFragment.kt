package com.mbds.newsletter.fragments

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.mbds.newsletter.data.ArticleRepository
import com.mbds.newsletter.models.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleListFragment : Fragment() {
    /**
     * Récupère la liste des articles dans un thread secondaire
     */
    private fun getArticles() {
        lifecycleScope.launch(Dispatchers.IO) {
            val articles = ArticleRepository.getInstance().getArticles()
        }
    }

    /**
     * Rempli le recyclerview avec les données récupérées dans le web service
     * Cette action doit s'effectuer sur le thread principale
     * Car on ne peut mas modifier les éléments de vue dans un thread secondaire
     */
    private fun bindData(articles: List<Article>) {
        lifecycleScope.launch(Dispatchers.Main) {
            //créer l'adapter
            //associer l'adapteur au recyclerview
        }
    }
}