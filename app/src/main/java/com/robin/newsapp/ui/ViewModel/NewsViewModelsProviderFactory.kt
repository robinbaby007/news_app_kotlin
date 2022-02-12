package com.robin.newsapp.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.robin.newsapp.Repository.NewsRepository

class NewsViewModelsProviderFactory (private val newsRepository: NewsRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }
}