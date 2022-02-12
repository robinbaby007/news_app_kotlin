package com.robin.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.robin.newsapp.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {

    private lateinit var binding:FragmentArticleBinding

    private val articleArgs:ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentArticleBinding.inflate(inflater)

        val article= articleArgs.article
        binding.webView.apply {
            webChromeClient= WebChromeClient()
            loadUrl(article.url)
        }
         return binding.root
    }


}