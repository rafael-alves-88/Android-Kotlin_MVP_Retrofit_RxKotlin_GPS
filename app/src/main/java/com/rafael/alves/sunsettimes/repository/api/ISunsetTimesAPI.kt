package com.rafael.alves.sunsettimes.repository.api

import com.rafael.alves.sunsettimes.model.SunsetTimes
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ISunsetTimesAPI {

    @GET("json")
    fun getSunsetTimes(@QueryMap fields: Map<String, String>): Observable<SunsetTimes>
}