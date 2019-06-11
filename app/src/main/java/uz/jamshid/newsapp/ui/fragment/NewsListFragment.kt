package uz.jamshid.newsapp.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import uz.jamshid.newsapp.R
import uz.jamshid.newsapp.core.base.BaseListFragment
import uz.jamshid.newsapp.core.extension.failure
import uz.jamshid.newsapp.core.extension.observe
import uz.jamshid.newsapp.core.extension.putExtra
import uz.jamshid.newsapp.core.extension.viewModel
import uz.jamshid.newsapp.core.model.News
import uz.jamshid.newsapp.ui.adapter.NewsAdapter
import uz.jamshid.newsapp.ui.viewmodel.NewsListViewModel

class NewsListFragment: BaseListFragment() {

    private lateinit var newsListViewModel: NewsListViewModel
    var key = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        newsListViewModel = viewModel(viewModelFactory){
            observe(newsList, ::renderNewsList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        key = getString(R.string.technology)
        if(arguments!=null)
            key = arguments?.getString("key")!!

        fetchNews()
    }

    private fun fetchNews(){

        when(key){
            getString(R.string.technology) -> newsListViewModel.getTechNews()
            getString(R.string.sport) -> newsListViewModel.getSportNews()
            getString(R.string.business) -> newsListViewModel.getBusinessNews()
            getString(R.string.politics) -> newsListViewModel.getPoliticNews()
            else -> newsListViewModel.getPoliticNews()
        }
    }

    private fun renderNewsList(list: MutableList<News?>?){
        setItems(list!!)
    }

    override fun onItemClicked(news: News) {
        fragmentManager?.beginTransaction()?.add(R.id.main_container, InfoFragment().putExtra("news", news), "INFO_FRAGMENT")?.commit()
    }

    private fun handleFailure(error: String?){
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun loadMore(adapter: NewsAdapter) {
        adapter.list.add(null)
        adapter.notifyItemInserted(adapter.list.size-1)
        newsListViewModel.page++
        fetchNews()
    }

}