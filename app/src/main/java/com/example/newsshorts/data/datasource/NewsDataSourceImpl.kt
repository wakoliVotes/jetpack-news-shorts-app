package com.example.newsshorts.data.datasource

import com.example.newsshorts.data.api.ApiService
import com.example.newsshorts.data.entity.NewsResponse
import jakarta.inject.Inject
import retrofit2.Response

class NewsDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : NewsDataSource {
    override fun getNewsHeadline(country: String): Response<NewsResponse> {
        return apiService.getNewsHeadline(country)
    }

}