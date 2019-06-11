package uz.jamshid.newsapp.core.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import uz.jamshid.newsapp.core.model.NewsResponse

interface NetworkService {

    @GET("top-headlines")
    fun getNews(@Query("country") country: String,
                @Query("category") category: String,
                @Query("page") page: Int,
                @Query("apiKey") apiKey: String): Single<NewsResponse>
}