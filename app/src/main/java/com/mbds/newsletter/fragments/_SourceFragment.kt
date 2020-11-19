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
import com.mbds.newsletter.data.SourceRepository
import com.mbds.newsletter.data.adapters.ListSourcesAdapter
import com.mbds.newsletter.data.adapters.ListSourcesHandler
import com.mbds.newsletter.models.SourceQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class _SourceFragment: Fragment(), ListSourcesHandler {
    private lateinit var recyclerView: RecyclerView
    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_chips_fragment, container, false)
        recyclerView = view.findViewById(R.id.chips_list)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSources()

        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.articles_list)
        }
    }

    /**
     * Récupère la liste des éditeurs dans un thread secondaire
     */
    override fun getSources() {
        lifecycleScope.launch(Dispatchers.IO) {
            val sources = SourceRepository.getInstance().getSources()
            bindData(sources)
        }
    }

    override fun getArticles() {
        TODO("Not yet implemented")
    }

    /**
     * Rempli le recyclerview avec les données récupérées dans le web service
     * Cette action doit s'effectuer sur le thread principale
     * Car on ne peut mas modifier les éléments de vue dans un thread secondaire
     */
    private fun bindData(sources: SourceQuery) {
        lifecycleScope.launch(Dispatchers.Main) {
            val adapter = ListSourcesAdapter(sources, this@_SourceFragment)
            recyclerView.adapter = adapter
        }
    }
}