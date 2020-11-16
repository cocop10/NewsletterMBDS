package com.mbds.newsletter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mbds.newsletter.NavigationListener
import com.mbds.newsletter.R

class AboutUsFragment : Fragment(){
    lateinit var homeButton : FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.about_us, container, false)
        homeButton = view.findViewById(R.id.btn_home)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeButton.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(HomeFragment())
                it.updateTitle(R.string.home)
            }
        }
    }
}