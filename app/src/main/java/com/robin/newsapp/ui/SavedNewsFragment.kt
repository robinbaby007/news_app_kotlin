package com.robin.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.robin.newsapp.Adapter.NewsAdapter
import com.robin.newsapp.R
import com.robin.newsapp.databinding.FragmentBreakingNewsBinding
import com.robin.newsapp.databinding.FragmentSavedNewsBinding

class SavedNewsFragment : Fragment() {

    lateinit var binding: FragmentSavedNewsBinding
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentSavedNewsBinding.inflate(inflater)
        val viewModel=(activity as NewsActivity).newsViewModel

        setRecyclerView()


        return binding.root
    }


    private fun setRecyclerView() {
        newsAdapter= NewsAdapter()
        binding.rvSavedNews.apply {
            adapter=newsAdapter
            layoutManager= LinearLayoutManager(activity)
        }

        newsAdapter.setItemClick {
            val bundle = Bundle()
                .apply {
                    putSerializable("article", it)
                }
            findNavController().navigate(R.id.action_searchNewsFragment_to_articleFragment, bundle)
        }

    }


}