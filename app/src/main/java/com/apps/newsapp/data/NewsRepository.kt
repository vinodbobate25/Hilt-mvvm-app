package com.apps.newsapp.data

import com.apps.newsapp.data.api.ApiHelper
import javax.inject.Inject

class NewsRepository @Inject constructor(private  val apiHelper: ApiHelper) {
    suspend fun getNews()=apiHelper.getNews()
}