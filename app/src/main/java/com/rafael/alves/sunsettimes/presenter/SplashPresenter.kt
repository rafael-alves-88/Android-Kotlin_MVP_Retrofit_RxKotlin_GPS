package com.rafael.alves.sunsettimes.presenter

import android.app.Activity
import android.content.Intent
import com.rafael.alves.sunsettimes.presenter.contract.TimerListener
import com.rafael.alves.sunsettimes.view.ui.CityListActivity

class SplashPresenter(private var mActivity: Activity) : BasePresenter() {

    fun startTimerToMainActivity(timerListener: TimerListener) {
        super.startTimer(5, timerListener)
    }

    fun startCityListActivity() {
        val intent = Intent(mActivity, CityListActivity::class.java)
        mActivity.startActivity(intent)
        mActivity.finish()
    }
}