package com.apps.newsapp.data.api

import com.apps.newsapp.data.model.ApiNews
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private  val newsApiService: NewsApiService): ApiHelper {
    override suspend fun getNews(): ApiNews =newsApiService.getApiNews()


}