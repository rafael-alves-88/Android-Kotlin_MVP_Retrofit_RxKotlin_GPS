package com.rafael.alves.sunsettimes.presenter

import com.rafael.alves.sunsettimes.presenter.contract.TimerListener
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit

open class BasePresenter {

    fun startTimer(seconds: Long, timerListener: TimerListener) {
        Observable.interval(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .take(seconds)
                .subscribeBy(
                        onComplete = { timerListener.onTimerFinishedCounting() },
                        onError = { timerListener.onTimerError(it) }
                )
    }
}