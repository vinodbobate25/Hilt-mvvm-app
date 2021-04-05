package com.apps.newsapp.data.api

import com.apps.newsapp.data.model.ApiNews

interface ApiHelper {
    suspend fun getNews():ApiNews
}