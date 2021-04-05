package com.apps.newsapp.data.api

import com.apps.newsapp.data.model.ApiNews
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getApiNews(  @Query("sources") sources:String="techcrunch",@Query("apiKey") apiKey:String="2df1e5a618b9443dbc156f63c646fd20"):ApiNews
}