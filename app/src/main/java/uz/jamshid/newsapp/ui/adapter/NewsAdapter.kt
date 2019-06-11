package uz.jamshid.newsapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import uz.jamshid.newsapp.R
import uz.jamshid.newsapp.core.base.BaseViewHolder
import uz.jamshid.newsapp.core.extension.inflate
import uz.jamshid.newsapp.core.extension.loadImage
import uz.jamshid.newsapp.core.manager.TimeManager
import uz.jamshid.newsapp.core.model.News

class NewsAdapter(var list: MutableList<News?>, var itemClick: OnItemClick?=null): RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        if(viewType == 1)
            return ProgressViewHolder(ProgressBar(parent.context))
        return NewsViewHolder(parent.inflate(R.layout.news_item_layout))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(list[position] == null)
            1
        else 0
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if(holder is NewsViewHolder)
            holder.onBind(position)
    }

    inner class NewsViewHolder(itemView: View): BaseViewHolder(itemView) {

        override fun onBind(position: Int) {
            val news = list[position]
            itemView.findViewById<TextView>(R.id.tvTitle).text = if(news?.title.isNullOrEmpty()) "" else news?.title
            itemView.findViewById<TextView>(R.id.tvDescription).text = if (news?.description.isNullOrEmpty()) "" else news?.description
            itemView.findViewById<ImageView>(R.id.ivNews).loadImage(news?.urlToImage)
            itemView.findViewById<TextView>(R.id.tvTime).text = TimeManager.convertToTextTime(news?.publishedAt!!)

            itemView.setOnClickListener{
                itemClick?.onClick(news)
            }
        }

    }

    inner class ProgressViewHolder(itemView: View): BaseViewHolder(itemView){

        override fun onBind(position: Int) {

        }

    }

    interface OnItemClick{
        fun onClick(news: News)
    }

}