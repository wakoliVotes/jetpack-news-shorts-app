package com.example.newsshorts.ui.viewmodel

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
        // coroutines
        /*
        * Coroutines in Jetpack Compose are a way to perform asynchronous (non-blocking) tasks without freezing the user interface (UI). They allow your app to do work in the background—such as making network requests, reading a database, or waiting for a delay—while keeping the app responsive.
        Think of it this way:

        Without coroutines: The UI waits until a task finishes, causing the app to freeze.
        With coroutines: The task runs in the background, and the UI remains smooth and interactive.
        */
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