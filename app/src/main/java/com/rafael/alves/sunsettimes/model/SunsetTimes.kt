package com.rafael.alves.sunsettimes.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SunsetTimes {

    @SerializedName("results")
    @Expose
    var results: Results? = null

    @SerializedName("status")
    @Expose
    var status: String? = null
}