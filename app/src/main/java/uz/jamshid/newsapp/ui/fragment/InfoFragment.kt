package uz.jamshid.newsapp.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.info_layout.*
import uz.jamshid.newsapp.R
import uz.jamshid.newsapp.core.extension.loadImage
import uz.jamshid.newsapp.core.model.News

class InfoFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.info_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var news: News?
        if(arguments!=null) {
            news = arguments?.getSerializable("news") as News
            populateData(news)
        }

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun populateData(news: News){
        ivPhoto.loadImage(news.urlToImage)
        tvTitle.text = if(news.title.isNullOrEmpty()) "" else news.title
        tvContent.text = if(news.content.isNullOrEmpty()) "" else news.content
    }
}