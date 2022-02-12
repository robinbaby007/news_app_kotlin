package com.robin.newsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.robin.newsapp.Adapter.NewsAdapter
import com.robin.newsapp.R
import com.robin.newsapp.Util.Resource
import com.robin.newsapp.databinding.FragmentBreakingNewsBinding
import com.robin.newsapp.databinding.FragmentSavedNewsBinding
import com.robin.newsapp.databinding.FragmentSearchNewsBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Response


class SearchNewsFragment : Fragment() {

    lateinit var binding: FragmentSearchNewsBinding
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSearchNewsBinding.inflate(inflater)
        val viewModel=(activity as NewsActivity).newsViewModel

        setRecyclerView();

        viewModel.searchNews.observe(viewLifecycleOwner) {

            when (it) {

               is Resource.Success -> {
                   hideProgressBar()
                   it.data?.let { articles ->
                       newsAdapter.differ.submitList(articles.articles)
                   }
               }

                is Resource.Failure->{
                    hideProgressBar()
                    it.data?.let {status->
                        Log.e( "BreakingNewsError: ", status.status)
                    }
                }
                is Resource.Loading->{
                    showProgressBar()
                }

            }

        }


        binding.etSearch.addTextChangedListener{
             lifecycleScope.launch {
                 delay(2000)
                 viewModel.searchNews(it.toString())
             }
        }


        return binding.root
    }

    private fun hideProgressBar(){
        binding.paginationProgressBar.visibility=View.GONE
    }
    private fun showProgressBar(){
        binding.paginationProgressBar.visibility=View.VISIBLE
    }

    private fun setRecyclerView() {
        newsAdapter=NewsAdapter()
         binding.rvSearchNews.apply {
             adapter=newsAdapter
             layoutManager=LinearLayoutManager(activity)
         }

    }


}