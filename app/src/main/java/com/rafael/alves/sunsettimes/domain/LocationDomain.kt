package com.rafael.alves.sunsettimes.domain

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import java.io.IOException
import java.util.*

class LocationDomain {

    fun getAddress(context: Context, location: Location?) : Address? {
        var address: Address? = null

        if (Geocoder.isPresent()) {
            try {
                val geoCoder = Geocoder(context, Locale.getDefault())
                val addresses = geoCoder.getFromLocation(location?.latitude!!, location.longitude, 1)
                if (addresses != null && addresses.size > 0) {
                    address = addresses[0]
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return address
    }

    fun getLocationByCityName(context: Context, city: String): List<Address>? {
        var locationList: List<Address>? = null
        if (Geocoder.isPresent()) {
            try {
                val gc = Geocoder(context)
                val addresses = gc.getFromLocationName(city, 5) // get the found Address Objects

                locationList = ArrayList(addresses.size) // A list to save the coordinates if they are available
                addresses.filterTo(locationList) { it.hasLatitude() && it.hasLongitude() }
            } catch (e: IOException) {
                // TODO - MSG
            }
        }

        return locationList
    }
}