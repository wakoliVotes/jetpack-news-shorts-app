package com.example.newsshorts.data.api

import com.example.newsshorts.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getNewsHeadline(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = "YOUR_API_KEY"
    ) : Response<NewsResponse>
}


// GET https://newsapi.org/v2/top-headlines?country=us&apiKey=f6c71f8f3c8f478e8d8fa2c9e29d5127