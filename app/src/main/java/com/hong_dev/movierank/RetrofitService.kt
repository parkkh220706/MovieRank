package com.hong_dev.movierank

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")

    fun getBoxOffice(
        @Query("key") key: String,
        @Query("targetDt") targetDt: String
    ): Call<MovieResponse>
}