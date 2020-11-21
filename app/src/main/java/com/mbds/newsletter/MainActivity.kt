package com.mbds.newsletter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.mbds.newsletter.fragments.AboutUsFragment
import com.mbds.newsletter.fragments.ArticleListFragment
import com.mbds.newsletter.fragments.FavArticleListFragment
import com.mbds.newsletter.fragments.MainFragment

class MainActivity : AppCompatActivity(), NavigationListener {
    private lateinit var toolbar: Toolbar
    private lateinit var aboutUs: ImageButton
    private lateinit var favoriteList: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        aboutUs = findViewById(R.id.item_about_button)
        favoriteList = findViewById(R.id.item_list_favorite_button)

        showFragment(MainFragment())

        aboutUs.setOnClickListener {
            showFragment(AboutUsFragment())
        }

        favoriteList.setOnClickListener {
            showFragment(FavArticleListFragment())
        }
    }

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }

    override fun updateTitle(title: Int) {
        toolbar.setTitle(title)
    }

    override fun addChildFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.beginTransaction().apply {
            replace(frameId, fragment)
            addToBackStack(null)
        }.commit()
    }
}