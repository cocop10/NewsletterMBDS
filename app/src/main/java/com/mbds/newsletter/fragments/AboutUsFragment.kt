package com.mbds.newsletter.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.mbds.newsletter.NavigationListener
import com.mbds.newsletter.R

class AboutUsFragment : Fragment() {
    private lateinit var link: TextView
    private lateinit var corentin: TextView
    private lateinit var nicolas: TextView
    private lateinit var vincent: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.about_us_fragment, container, false)
        link = view.findViewById(R.id.link)
        corentin = view.findViewById(R.id.corentin)
        nicolas = view.findViewById(R.id.nicolas)
        vincent = view.findViewById(R.id.vincent)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.title_aboutus)
        }

        link.setOnClickListener {
            context?.let {
                openNewTabWindow("https://github.com/ncao98/NewsletterMBDS/", it)
            }
        }

        corentin.setOnClickListener {
            context?.let {
                openNewTabWindow("https://www.linkedin.com/in/corentinpoirier/", it)
            }
        }

        nicolas.setOnClickListener {
            context?.let {
                openNewTabWindow("https://www.linkedin.com/in/caon/", it)
            }
        }

        vincent.setOnClickListener {
            context?.let {
                openNewTabWindow("https://www.linkedin.com/in/vincent-guinaldo-805b531a1/", it)
            }
        }
    }

    fun openNewTabWindow(urls: String, context: Context) {
        val uris = Uri.parse(urls)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        context.startActivity(intents)
    }
}