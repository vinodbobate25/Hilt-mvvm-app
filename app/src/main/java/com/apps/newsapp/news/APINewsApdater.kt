package com.apps.newsapp.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.newsapp.R
import com.apps.newsapp.data.model.ApiNews
import com.apps.newsapp.data.model.ArticlesItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.listitem.view.*

class APINewsApdater(private  val news:ArrayList<ArticlesItem>): RecyclerView.Adapter<APINewsApdater.ViewHolder>() {

    class ViewHolder( itemView:View) :RecyclerView.ViewHolder(itemView){

        fun bind(articlesItem: ArticlesItem)
        {
            itemView.txtHeader.text=articlesItem.title
            itemView.txtNews.text=articlesItem.description
            Glide.with(itemView.imageViewAvatar.context)
                    .load(articlesItem.urlToImage)
                    .into(itemView.imageViewAvatar)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).
    inflate(R.layout.listitem,parent,false))

    override fun getItemCount()=news.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =holder.bind(news[position])
    fun addData(newList: List<ArticlesItem>) {
        news.addAll(newList)
    }
}