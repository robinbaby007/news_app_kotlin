package com.robin.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robin.newsapp.R
import com.robin.newsapp.databinding.FragmentBreakingNewsBinding
import com.robin.newsapp.databinding.FragmentSavedNewsBinding

class SavedNewsFragment : Fragment() {

    lateinit var binding: FragmentSavedNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentSavedNewsBinding.inflate(inflater)
        val viewModel=(activity as NewsActivity).newsViewModel




        return binding.root
    }


}