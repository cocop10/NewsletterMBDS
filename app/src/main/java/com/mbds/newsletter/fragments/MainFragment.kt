package com.mbds.newsletter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mbds.newsletter.NavigationListener
import com.mbds.newsletter.R

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
            it.addChildFragment(_HeadlinesFragment(), R.id.list_articles_fragment)
            it.addChildFragment(_SourceFragment(), R.id.list_sources_fragment)
            it.addChildFragment(_CategoryFragment(), R.id.list_categories_fragment)
            it.addChildFragment(_CountryFragment(), R.id.list_countries_fragment)
            it.updateTitle(R.string.app_name)
        }
    }
}