package com.rafael.alves.sunsettimes.view.ui

import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v4.content.ContextCompat

open class BaseActivity : AppCompatActivity() {

    fun hasGPSPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    fun requestGPSPermission() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                0)
    }
}
