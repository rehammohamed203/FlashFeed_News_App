package com.reham11203.flashfeed.ui.home.fragments.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.reham11203.flashfeed.api.models.news.News
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
            itemBinding.title.text = news?.title
            itemBinding.author.text = news?.author

            Glide.with(itemBinding.root)
                .load(news?.urlToImage)
                .into(itemBinding.image)

            val formatedDate = news?.getPublishedAtInMillis()?.let { TimeAgo.using(it) }
            itemBinding.date.text = formatedDate
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun changeData(newList: List<News?>?) {
        this.newsList = newList
        notifyDataSetChanged()
    }
}