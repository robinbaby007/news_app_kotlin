package com.robin.newsapp.Models

data class NewsResp(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)