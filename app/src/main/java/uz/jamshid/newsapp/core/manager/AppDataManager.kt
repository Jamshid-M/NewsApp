package uz.jamshid.newsapp.core.manager

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uz.jamshid.newsapp.core.model.NewsResponse
import uz.jamshid.newsapp.core.network.NetworkService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(var service: NetworkService) : DataManager {

    override fun getNews(country: String, category: String, page: Int, apiKey: String): Single<NewsResponse> {
        return service.getNews(country, category, page, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}