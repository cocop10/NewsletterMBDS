package com.mbds.newsletter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.mbds.newsletter.fragments.ArticleListFragment
import com.mbds.newsletter.fragments.MainFragment

class MainActivity : AppCompatActivity(), NavigationListener {
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        showFragment(MainFragment())
//        showFragment(ArticleListFragment())
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