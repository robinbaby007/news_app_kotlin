package com.robin.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.robin.newsapp.Adapter.NewsAdapter
import com.robin.newsapp.R
import com.robin.newsapp.Repository.NewsRepository
import com.robin.newsapp.databinding.ActivityNewsBinding
import com.robin.newsapp.db.ArticleDataBase
import com.robin.newsapp.ui.ViewModel.NewsViewModel
import com.robin.newsapp.ui.ViewModel.NewsViewModelsProviderFactory

class NewsActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityNewsBinding
     lateinit var newsViewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository=NewsRepository(ArticleDataBase(this))
        val viewModelFactory=NewsViewModelsProviderFactory(repository)
        newsViewModel=ViewModelProvider(this,viewModelFactory)[NewsViewModel::class.java]

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
          binding.bottomNavigationView.setupWithNavController(navHostFragment.navController)


       /*  newsAdapter.setItemClick {
            it.id
        }*/


    }
}