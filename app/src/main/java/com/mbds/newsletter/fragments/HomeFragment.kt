package com.mbds.newsletter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.mbds.newsletter.NavigationListener
import com.mbds.newsletter.R

class HomeFragment :Fragment() {

    lateinit var aboutUsButton : Button
    lateinit var listArticlesButton : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home, container, false)
        aboutUsButton = view.findViewById(R.id.btn_a_propos)
        listArticlesButton = view.findViewById(R.id.btn_list_articles)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        aboutUsButton.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(AboutUsFragment())
                it.updateTitle(R.string.apropos_page)
            }
        }

        listArticlesButton.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(ArticleListFragment())
                it.updateTitle(R.string.articles_list)
            }
        }

    }
}