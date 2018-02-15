package com.rafael.alves.sunsettimes.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Results {

    @SerializedName("sunrise")
    @Expose
    var sunrise: String? = null

    @SerializedName("sunset")
    @Expose
    var sunset: String? = null

    @SerializedName("solar_noon")
    @Expose
    var solarNoon: String? = null

    @SerializedName("day_length")
    @Expose
    var dayLength: String? = null

    @SerializedName("civil_twilight_begin")
    @Expose
    var civilTwilightBegin: String? = null

    @SerializedName("civil_twilight_end")
    @Expose
    var civilTwilightEnd: String? = null

    @SerializedName("nautical_twilight_begin")
    @Expose
    var nauticalTwilightBegin: String? = null

    @SerializedName("nautical_twilight_end")
    @Expose
    var nauticalTwilightEnd: String? = null

    @SerializedName("astronomical_twilight_begin")
    @Expose
    var astronomicalTwilightBegin: String? = null

    @SerializedName("astronomical_twilight_end")
    @Expose
    var astronomicalTwilightEnd: String? = null
}
