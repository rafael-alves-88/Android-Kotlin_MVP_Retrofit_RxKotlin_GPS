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
import android.location.Geocoder
import java.io.IOException
import com.rafael.alves.sunsettimes.model.AddressCode


abstract class BaseLocationActivity : BaseActivity(),
        LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private val INTERVAL: Long = 6000 * 10
    private val FASTEST_INTERVAL: Long = 6000 * 5

    private lateinit var mLocationRequest: LocationRequest
    private lateinit var mGoogleApiClient: GoogleApiClient

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
        if (hasGPSPermission()) {
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
        val addressCode = AddressCode()

        if (Geocoder.isPresent()) {
            try {
                val geoCoder = Geocoder(this, Locale.getDefault())
                val addresses = geoCoder.getFromLocation(location?.latitude!!, location.longitude, 1)
                if (addresses != null && addresses.size > 0) {

                    //val address = addresses[0].getAddressLine(0)
                    val city = addresses[0].locality
                    val state = addresses[0].adminArea
                    val country = addresses[0].countryName
                    val countryCode = addresses[0].countryCode.toLowerCase()

                    addressCode.address = String.format("%s, %s - %s", city, state, country)
                    addressCode.countryCode = countryCode
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return addressCode
    }

    fun getLocationByCityName(city: String): List<Address>? {
        var locationList: List<Address>? = null
        if (Geocoder.isPresent()) {
            try {
                val gc = Geocoder(this)
                val addresses = gc.getFromLocationName(city, 5) // get the found Address Objects

                locationList = ArrayList(addresses.size) // A list to save the coordinates if they are available
                addresses.filterTo(locationList) { it.hasLatitude() && it.hasLongitude() }
            } catch (e: IOException) {
                // handle the exception
            }
        }

        return locationList
    }
    //endregion
}
