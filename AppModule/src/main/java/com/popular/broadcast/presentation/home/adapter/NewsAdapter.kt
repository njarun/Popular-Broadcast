package com.popular.broadcast.presentation.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.popular.broadcast.databinding.LayoutNewsItemBinding
import com.popular.broadcast.domain.schedule.model.News

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val newsList: ArrayList<News> = ArrayList()
    private lateinit var context: Context

    class NewsViewHolder(binding: LayoutNewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val title = binding.titleTv
        val byLine = binding.byLineTv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        context = parent.context
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

    @SuppressLint("NotifyDataSetChanged")
    fun update(newsList: List<News>) {

        this.newsList.run {

            clear()
            addAll(newsList)
            notifyDataSetChanged()
        }
    }
}