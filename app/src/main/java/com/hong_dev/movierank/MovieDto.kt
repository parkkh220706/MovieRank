package com.hong_dev.movierank

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieDto(
    @SerializedName("rank")
    var rank: String?,
    @SerializedName("movieNm")
    var movieNm: String?,
    @SerializedName("openDt")
    var openDt: String?,
    @SerializedName("audiAcc")
    var audiAcc: String?
) : Serializable{

}
