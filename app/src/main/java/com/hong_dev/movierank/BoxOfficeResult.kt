package com.hong_dev.movierank

import com.google.gson.annotations.SerializedName

data class BoxOfficeResult(
    @SerializedName("dailyBoxOfficeList")
    var dailyBoxOfficeResult: List<MovieDto> = arrayListOf()
)
