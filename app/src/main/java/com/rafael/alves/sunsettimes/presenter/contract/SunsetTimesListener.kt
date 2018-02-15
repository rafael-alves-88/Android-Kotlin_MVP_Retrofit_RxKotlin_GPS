package com.rafael.alves.sunsettimes.presenter.contract

import com.rafael.alves.sunsettimes.model.SunsetTimes

interface SunsetTimesListener : BaseListener {

    fun onSuccess(sunsetTimes: SunsetTimes?)

    fun onFail()
}