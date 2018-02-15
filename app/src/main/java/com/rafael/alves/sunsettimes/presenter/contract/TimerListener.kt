package com.rafael.alves.sunsettimes.presenter.contract

interface TimerListener {

    /**
     * Triggers when handler's timer has finished counting
     */
    fun onTimerFinishedCounting()

    /**
     * Error while counting
     *
     * @param throwable - Exception
     */
    fun onTimerError(throwable: Throwable)
}