package com.reham11203.flashfeed.ui.home.fragments.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reham11203.domain.model.News
import com.reham11203.flashfeed.databinding.ItemNewsBinding

class NewsAdapter(var newsList: List<News?>? = null) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsList?.get(position))
    }

    override fun getItemCount(): Int = newsList?.size ?: 0

    class ViewHolder(val itemBinding: ItemNewsBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(news: News?) {
            itemBinding.newsItem = news
            itemBinding.invalidateAll()


        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun changeData(newList: List<News?>?) {
        this.newsList = newList
        notifyDataSetChanged()
    }
}