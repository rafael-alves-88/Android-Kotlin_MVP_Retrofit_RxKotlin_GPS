package com.rafael.alves.sunsettimes.domain

import com.rafael.alves.sunsettimes.presenter.contract.SunsetTimesListener
import com.rafael.alves.sunsettimes.repository.api.SunsetTimesAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CityListDomain {

    private var mSunsetTimesAPI: SunsetTimesAPI = SunsetTimesAPI()

    fun getSunsetTimes(latitude: String, longitude: String, listener: SunsetTimesListener) {
        listener.onRequestStarted()
        mSunsetTimesAPI.getSunsetTimesAPI()
                .getSunsetTimes(mSunsetTimesAPI.getQueryParameters(latitude, longitude))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                        listener.onError(it)
                        listener.onRequestFinished()
                    },
                        onComplete = {
                        listener.onRequestFinished()
                    },
                        onNext = {
                            if (it.status == "OK") {
                                listener.onSuccess(it)
                            } else {
                                listener.onFail()
                            }
                    }
                )
    }
}