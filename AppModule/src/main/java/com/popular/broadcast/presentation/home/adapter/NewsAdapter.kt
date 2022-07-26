package com.popular.broadcast.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.popular.broadcast.databinding.LayoutNewsItemBinding
import com.popular.broadcast.domain.schedule.model.News

class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {

    private var newsList: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutNewsItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int = newsList.size

    fun update(newsList: List<News>) {

        this.newsList.clear()
        this.newsList.addAll(newsList)
        notifyDataSetChanged()
    }
}