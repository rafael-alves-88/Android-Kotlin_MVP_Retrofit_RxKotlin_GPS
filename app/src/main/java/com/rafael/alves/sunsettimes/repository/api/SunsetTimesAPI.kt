package com.rafael.alves.sunsettimes.repository.api

import java.util.HashMap

class SunsetTimesAPI : BaseAPI() {

    private var PARAM_LATITUDE = "lat"
    private var PARAM_LONGITUDE = "lng"
    private var PARAM_DATE = "date"

    private var VALUE_DATE = "today"

    private var mSunsetTimesAPI: ISunsetTimesAPI = super.getRetrofit().create(ISunsetTimesAPI::class.java)

    fun getSunsetTimesAPI(): ISunsetTimesAPI {
        return mSunsetTimesAPI
    }

    fun getQueryParameters(latitude: String, longitude: String): Map<String, String> {
        val companyQueryParams = HashMap<String, String>()
        companyQueryParams.put(PARAM_LATITUDE, latitude)
        companyQueryParams.put(PARAM_LONGITUDE, longitude)
        companyQueryParams.put(PARAM_DATE, VALUE_DATE)

        return companyQueryParams
    }
}