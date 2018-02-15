package com.rafael.alves.sunsettimes.view.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.rafael.alves.sunsettimes.R
import com.rafael.alves.sunsettimes.presenter.SplashPresenter
import com.rafael.alves.sunsettimes.presenter.contract.TimerListener

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        checkPermissions()
    }

    private val mSplashPresenter = SplashPresenter(this)

    private fun startCityListActivity() {
        mSplashPresenter.startTimerToMainActivity(object : TimerListener {
            override fun onTimerFinishedCounting() {
                mSplashPresenter.startCityListActivity()
            }

            override fun onTimerError(throwable: Throwable) {
                // TODO - msg
            }
        })
    }

    //region [ Permissions ]
    private fun checkPermissions() {
        if (hasGPSPermission()) {
            startCityListActivity()
        } else {
            requestGPSPermission()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            0 -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCityListActivity()
            } else {
                // TODO - msg
                Toast.makeText(this@SplashActivity, "No permission to access location!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
    //endregion
}
