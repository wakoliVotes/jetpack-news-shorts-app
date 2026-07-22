package com.example.newsshorts.ui.viewmodel

import android.R.attr.country
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsshorts.data.AppConstants
import com.example.newsshorts.data.entity.NewsResponse
import com.example.newsshorts.ui.repository.NewsRepository
import com.example.newsshorts.util.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel(){

    private val _news : MutableStateFlow<ResourceState<NewsResponse>> = MutableStateFlow(
        ResourceState.Loading())

    val news : StateFlow<ResourceState<NewsResponse>> = _news

    init {
        getNews(AppConstants.COUNTRY)
    }

    private fun getNews(country: String){
        viewModelScope.launch (Dispatchers.IO) {
            newsRepository.getNewsHeadline(country)
                .collectLatest {
                    newsResponse ->
                    _news.value = newsResponse
                }

        }
    }

    companion object {
        const val TAG = "NewsViewModel"
    }

}