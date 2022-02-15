package com.robin.newsapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.robin.newsapp.Models.Article
import com.robin.newsapp.R
import com.robin.newsapp.Repository.NewsRepository
import com.robin.newsapp.databinding.ItemArticlePreviewBinding
import com.robin.newsapp.db.ArticleDataBase

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {


    private val diffCallBack=object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return  oldItem==newItem
        }

    }

    val differ= AsyncListDiffer(this,diffCallBack)

    inner class ArticleViewHolder(val itemArticlePreviewBinding: ItemArticlePreviewBinding):RecyclerView.ViewHolder(itemArticlePreviewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
         )
     }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        val article=differ.currentList[position]
        holder.itemArticlePreviewBinding.apply {
            Glide.with(holder.itemView.context).load(article.urlToImage).into(this.ivArticleImage)
            tvSource.text=article.source?.name
            tvTitle.text=article.title
            tvDescription.text=article.description
            tvPublishedAt.text=article.publishedAt


        }
        holder.itemView.setOnClickListener {
            clickListener(article)
        }
     }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }


    private lateinit var clickListener:((Article)->Unit)

     fun setItemClick(listener:(Article)->Unit){
        clickListener=listener
    }
}