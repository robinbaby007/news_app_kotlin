package com.robin.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.robin.newsapp.databinding.FragmentArticleBinding
import com.robin.newsapp.ui.ViewModel.NewsViewModel

class ArticleFragment : Fragment() {

    private lateinit var binding:FragmentArticleBinding
    private val articleArgs:ArticleFragmentArgs by navArgs()
    private lateinit var viewModel:NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentArticleBinding.inflate(inflater)
        viewModel=(activity as NewsActivity).newsViewModel
        val article= articleArgs.article
        binding.webView.apply {
            webChromeClient= WebChromeClient()
            article.url?.let { loadUrl(it) }
        }

        binding.fab.setOnClickListener{
            viewModel.saveArticle(article)
            Snackbar.make(binding.root,"Saved Successfully",Snackbar.LENGTH_SHORT).show()
        }

         return binding.root
    }


}