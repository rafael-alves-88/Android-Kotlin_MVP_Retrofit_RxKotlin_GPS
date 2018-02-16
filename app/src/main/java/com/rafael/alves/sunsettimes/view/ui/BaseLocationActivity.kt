package com.rafael.alves.sunsettimes.view.ui

import android.annotation.SuppressLint
import android.location.Address
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.rafael.alves.sunsettimes.utils.PlayServicesUtils
import java.text.DateFormat
import java.util.*
import com.rafael.alves.sunsettimes.model.AddressCode
import com.rafael.alves.sunsettimes.presenter.LocationPresenter

abstract class BaseLocationActivity : BaseActivity(),
        LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private val INTERVAL: Long = 6000 * 10
    private val FASTEST_INTERVAL: Long = 6000 * 5

    private lateinit var mLocationRequest: LocationRequest
    private lateinit var mGoogleApiClient: GoogleApiClient
    private val mLocationPresenter = LocationPresenter()

    abstract fun getCurrentLocationSunsetTimes(location: Location?, updateTime: String)

    //region [ LifeCycle ]
    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    override fun onStop() {
        super.onStop()
        if (mGoogleApiClient.isConnected) {
            mGoogleApiClient.disconnect()
        }
    }

    override fun onResume() {
        super.onResume()
        if (mGoogleApiClient.isConnected) {
            startLocationUpdates()
        } else {
            startFusedLocation()
        }
    }
    //endregion

    //region [ Location ]
    private fun createLocationRequest() {
        mLocationRequest = LocationRequest()
        mLocationRequest.interval = INTERVAL
        mLocationRequest.fastestInterval = FASTEST_INTERVAL
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    fun startFusedLocation() {
        if (PlayServicesUtils.isGooglePlayServicesAvailable(this)) {
            createLocationRequest()
            mGoogleApiClient = GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build()

            mGoogleApiClient.connect()
        } else {
            // TODO - Error msg
        }
    }

    override fun onLocationChanged(p0: Location?) {
        getCurrentLocationSunsetTimes(p0, DateFormat.getTimeInstance().format(Date()))
    }

    override fun onConnected(p0: Bundle?) {
        startLocationUpdates()
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        if (hasGPSPermission() && mGoogleApiClient.isConnected) {
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, mLocationRequest, this)
        }
    }

    override fun onConnectionSuspended(p0: Int) {
        // Empty
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        // TODO - msg
        Toast.makeText(this, "Connection Failed", Toast.LENGTH_SHORT).show()
    }

    private fun stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this)
    }

    fun getAddress(location: Location?) : AddressCode? {
        return mLocationPresenter.getAddress(this, location)
    }

    fun getLocationByCityName(city: String): List<Address>? {
        return mLocationPresenter.getLocationByCityName(this, city)
    }
    //endregion
}
