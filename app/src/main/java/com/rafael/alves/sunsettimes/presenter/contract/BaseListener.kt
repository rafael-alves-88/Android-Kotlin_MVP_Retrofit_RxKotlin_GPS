package com.rafael.alves.sunsettimes.presenter.contract

interface BaseListener {

    /**
     * API Request started
     */
    fun onRequestStarted()

    /**
     * API Request finished
     */
    fun onRequestFinished()

    /**
     * Error during API Request
     * @param e - Exception
     */
    fun onError(e: Throwable)
}