package com.popular.broadcast.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.popular.broadcast.databinding.LayoutNewsItemBinding
import com.popular.broadcast.domain.schedule.model.News

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newsList: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val viewBinding = LayoutNewsItemBinding.inflate(LayoutInflater.from(parent.context))
        return NewsViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        newsList[position].also {

            holder.title.text = it.title
            holder.byLine.text = it.byline
        }
    }

    override fun getItemCount(): Int = newsList.size

    fun update(newsList: List<News>) {

        val size = this.newsList.size
        this.newsList.clear()

        if(size > 0)
            notifyItemRangeRemoved(0, size)

        this.newsList.addAll(newsList)
        notifyItemInserted(0)
    }

    class NewsViewHolder(binding: LayoutNewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val title = binding.titleTv
        val byLine = binding.byLineTv
    }
}