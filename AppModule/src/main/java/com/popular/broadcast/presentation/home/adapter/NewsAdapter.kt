package com.popular.broadcast.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.popular.broadcast.databinding.LayoutNewsItemBinding
import com.popular.broadcast.domain.schedule.model.News
import com.popular.broadcast.presentation.base.handler.AppInterface

class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>(), AppInterface {

    private var newsList: ArrayList<News> = ArrayList()
    private var receiver: AppInterface? = null

    fun registerForItemClick(receiver: AppInterface) {
        this.receiver = receiver
    }

    fun update(newsList: List<News>) {

        val size = this.newsList.size

        this.newsList.clear()
        this.newsList.addAll(newsList)

        //if(size == this.newsList.size)
        //    notifyItemRangeChanged(0, size);
        /*else*/ notifyDataSetChanged()
    }

    override fun getItemCount(): Int = newsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutNewsItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(this, newsList[position])
    }

    override fun onCallback(vararg any: Any) {

        receiver?.let {
            if(any.isNotEmpty())
                it.onCallback(any[0])
            else it.onCallback()
        }
    }
}