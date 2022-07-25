package com.popular.broadcast.data.schedule.api

import com.popular.broadcast.BuildConfig
import com.popular.broadcast.data.schedule.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {

    //@Headers("mock:true")
    @GET("/svc/mostpopular/v2/mostviewed/{section}/{period}.json")
    suspend fun getNews(
        @Path("section") section: String,
        @Path("period") period: Int,
        @Query("api-key") key: String? = BuildConfig.API_KEY
    ): NewsResponse
}