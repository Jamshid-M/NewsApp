package uz.jamshid.newsapp.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import uz.jamshid.newsapp.core.base.BaseViewModel
import uz.jamshid.newsapp.core.manager.DataManager
import uz.jamshid.newsapp.core.model.News
import javax.inject.Inject

class NewsListViewModel @Inject constructor(dataManager: DataManager): BaseViewModel(dataManager) {

    var newsList: MutableLiveData<MutableList<News?>> = MutableLiveData()
    var page = 1
    var lang = "us"

    fun getPoliticNews(country: String = lang, page: Int = this.page){
        compositeDisposable.add(mDataManager.getNews(country, "politics", page, "e6ee668a90da4558be5f77273b2070b8").subscribe({
            newsList.value = it.articles
        },{
            failure.value = it.message
        }))
    }

    fun getSportNews(country: String = lang, page: Int = this.page){
        compositeDisposable.add(mDataManager.getNews(country, "sport", page, "e6ee668a90da4558be5f77273b2070b8").subscribe({
            newsList.value = it.articles
        },{
            failure.value = it.message
        }))
    }

    fun getBusinessNews(country: String = lang, page: Int = this.page){
        compositeDisposable.add(mDataManager.getNews(country, "business", page, "e6ee668a90da4558be5f77273b2070b8").subscribe({
            newsList.value = it.articles
        },{
            failure.value = it.message
        }))
    }

    fun getTechNews(country: String = lang, page: Int = this.page){
        compositeDisposable.add(mDataManager.getNews(country, "technology", page, "e6ee668a90da4558be5f77273b2070b8").subscribe({
            newsList.value = it.articles
        },{
            failure.value = it.message
        }))
    }
}