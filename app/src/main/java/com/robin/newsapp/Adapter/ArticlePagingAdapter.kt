package com.robin.newsapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.robin.newsapp.Models.Article
import com.robin.newsapp.databinding.ItemArticlePreviewBinding

class ArticlePagingAdapter:PagingDataAdapter<Article,ArticlePagingAdapter.ArticlePagingViewHolder>(diffUtil) {

    override fun onBindViewHolder(holder: ArticlePagingViewHolder, position: Int) {
        val article=getItem(position)

        if (article!=null)
            holder.bind(article)

        holder.itemView.setOnClickListener {
            article?.let(itemClick)
         }
    }

    class ArticlePagingViewHolder(private val itemArticlePreviewBinding: ItemArticlePreviewBinding):RecyclerView.ViewHolder(itemArticlePreviewBinding.root){

        fun bind(article: Article){
            itemArticlePreviewBinding.apply {
                Glide.with(itemView.context).load(article.urlToImage).into(this.ivArticleImage)
                tvSource.text=article.source?.name
                tvTitle.text=article.title
                tvDescription.text=article.description
                tvPublishedAt.text=article.publishedAt
            }
        }

    }

    companion object {

        private val diffUtil=object :DiffUtil.ItemCallback<Article>(){
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url==newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return  oldItem==newItem

            }

        }
    }

    private lateinit var itemClick: (Article) -> Unit

    fun setItemClick(itemClick:(Article)->Unit){
        this.itemClick=itemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlePagingViewHolder {
        return ArticlePagingViewHolder(ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
}