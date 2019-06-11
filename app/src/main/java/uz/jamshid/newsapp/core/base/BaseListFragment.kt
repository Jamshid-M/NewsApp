package uz.jamshid.newsapp.core.base

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.base_list_layout.*
import uz.jamshid.newsapp.R
import uz.jamshid.newsapp.core.extension.gone
import uz.jamshid.newsapp.core.extension.visible
import uz.jamshid.newsapp.core.model.News
import uz.jamshid.newsapp.ui.adapter.NewsAdapter

abstract class BaseListFragment: BaseFragment() {

    var isLoading = false
    var adapter: NewsAdapter?=null
    var endHasReached = false

    override fun getLayoutId(): Int {
        return R.layout.base_list_layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvNews.layoutManager = LinearLayoutManager(context)
    }

    open fun setItems(list: MutableList<News?>){
        progressBar.gone()
        rvNews.visible()

        if(!endHasReached) {
            if (isLoading) {
                adapter?.list?.removeAt(adapter?.list?.size!! - 1)
                adapter?.notifyItemRemoved(adapter?.list?.size!!)
                adapter?.list?.addAll(list)
                adapter?.notifyDataSetChanged()
                isLoading = false
            } else {

                adapter = NewsAdapter(list, object : NewsAdapter.OnItemClick {
                    override fun onClick(news: News) {
                        onItemClicked(news)
                    }
                })
                rvNews.adapter = adapter

                initScrollListener(list)
            }
        }
        if(list.size == 0)
            endHasReached = true
    }

    open fun onItemClicked(news: News){}

    private fun initScrollListener(list: MutableList<News?>){
        rvNews.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val llm: LinearLayoutManager = recyclerView.layoutManager as LinearLayoutManager

                if(!isLoading && !endHasReached){
                    if (llm.findLastCompletelyVisibleItemPosition() == list.size - 1){
                        loadMore(adapter!!)
                        isLoading = true
                    }
                }
            }
        })
    }

    open fun loadMore(adapter: NewsAdapter){
    }
}
