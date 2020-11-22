package com.mbds.newsletter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.mbds.newsletter.fragments.*

class MainActivity : AppCompatActivity(), NavigationListener, TextWatcher {
    private lateinit var toolbar: Toolbar
    private lateinit var aboutUs: ImageButton
    private lateinit var favoriteList: ImageButton
    private lateinit var queryTextEdit: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        queryTextEdit = findViewById(R.id.name)
        aboutUs = findViewById(R.id.item_about_button)
        favoriteList = findViewById(R.id.item_list_favorite_button)

        showFragment(MainFragment())

        queryTextEdit.addTextChangedListener(this)

        aboutUs.setOnClickListener {
            showFragment(AboutUsFragment())
        }

        favoriteList.setOnClickListener {
            showFragment(FavArticleListFragment())
        }

        queryTextEdit.setOnClickListener {
            if (!queryTextEdit.text.isNullOrEmpty()) {
                showFragment(
                    ArticleListFragment(
                        queryTextEdit.text.toString().replace(" ", "+"),
                        "MainActivity"
                    )
                )
            }
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

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {

    }
}