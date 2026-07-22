package com.example.newsshorts.data.datasource

import com.example.newsshorts.data.entity.NewsResponse
import retrofit2.Response

interface NewsDataSource {

    fun getNewsHeadline(country: String) : Response<NewsResponse>
}