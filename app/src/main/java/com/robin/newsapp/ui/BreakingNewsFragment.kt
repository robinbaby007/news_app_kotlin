package com.robin.newsapp.ui

import android.app.Activity
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.robin.newsapp.Adapter.NewsAdapter
import com.robin.newsapp.R
import com.robin.newsapp.Util.Resource
import com.robin.newsapp.databinding.FragmentBreakingNewsBinding


class BreakingNewsFragment : Fragment() {


    lateinit var binding:FragmentBreakingNewsBinding
    lateinit var newsAdapter: NewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentBreakingNewsBinding.inflate(inflater)
        val viewModel=(activity as NewsActivity).newsViewModel

        newsAdapter= NewsAdapter()
        setupRecyclerView()
        viewModel.getBreadingNews("in")


        viewModel.breakingNews.observe(viewLifecycleOwner, Observer {response->
            when(response){
                is Resource.Success->{
                    hideProgressBar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles)
                    }

                }
                is Resource.Failure->{
                    hideProgressBar()
                    response.message?.let {
                        Log.e( "BreakingNewsError: ", it)
                    }
                }
                is Resource.Loading->{
                    showProgressBar()
                }
            }


        })
         return binding.root
    }

    private fun hideProgressBar(){
        binding.paginationProgressBar.visibility=View.GONE
    }
    private fun showProgressBar(){
        binding.paginationProgressBar.visibility=View.VISIBLE
    }

    private fun setupRecyclerView() {

        binding.rvBreakingNews.apply {
            adapter=newsAdapter
            layoutManager=LinearLayoutManager(activity)
        }

        newsAdapter.setItemClick {


            val bundle=Bundle()
                .apply {
                    putSerializable("article",it)
                }
            Log.e("setupRecyclerView: ",Gson().toJson(it), )

            findNavController().navigate(R.id.action_breakingNewsFragment_to_articleFragment,bundle)
        }

     }


}