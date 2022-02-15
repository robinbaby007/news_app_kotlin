package com.robin.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
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

        viewModel.getAllSavedArticles().observe(viewLifecycleOwner){
            newsAdapter.differ.submitList(it)
        }

        val itemTouchHelper=object:ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position=viewHolder.absoluteAdapterPosition
                val article=newsAdapter.differ.currentList[position]
                viewModel.deleteArticle(article)

                Snackbar.make(binding.root,"Deleted Successfully",Snackbar.LENGTH_LONG).apply {
                    setAction("Undo"){
                        viewModel.saveArticle(article)
                    }
                    show()
                 }
            }

        }

        ItemTouchHelper(itemTouchHelper).apply {
            attachToRecyclerView(binding.rvSavedNews)
        }

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
            findNavController().navigate(R.id.action_savedNewsFragment_to_articleFragment, bundle)
        }

    }


}