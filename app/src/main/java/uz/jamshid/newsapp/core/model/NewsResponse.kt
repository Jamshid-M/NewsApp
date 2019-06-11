package uz.jamshid.newsapp.core.model

import java.io.Serializable

data class NewsResponse(val status: String, val totalResults: Int, val articles: MutableList<News?>): Serializable