package com.hong_dev.movierank

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitMovieAPI {
    @GET("/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json")
    fun getMovieList(
        @Query("key") key: String,
        @Query("targetDt") targetDt: String
    ): Call<MovieResponse>
}