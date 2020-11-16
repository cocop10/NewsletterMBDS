package com.mbds.newsletter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.mbds.newsletter.fragments.ArticleListFragment
import com.mbds.newsletter.fragments.HomeFragment

class MainActivity : AppCompatActivity(), NavigationListener {
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //showFragment(ArticleListFragment())
        showFragment(HomeFragment())
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
}