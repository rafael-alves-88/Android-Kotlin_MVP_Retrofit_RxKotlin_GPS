package com.rafael.alves.sunsettimes.presenter

import android.content.Context
import android.location.Address
import android.location.Location
import com.rafael.alves.sunsettimes.domain.LocationDomain
import com.rafael.alves.sunsettimes.model.AddressCode

class LocationPresenter {

    private val mLocationDomain = LocationDomain()

    fun getAddress(context: Context, location: Location?) : AddressCode? {
        val address = mLocationDomain.getAddress(context, location)
        val addressCode = AddressCode()

        //val address = addresses[0].getAddressLine(0)
        val city = address?.locality
        val state = address?.adminArea
        val country = address?.countryName
        val countryCode = address?.countryCode!!.toLowerCase()

        addressCode.address = String.format("%s, %s - %s", city, state, country)
        addressCode.countryCode = countryCode

        return addressCode
    }

    fun getLocationByCityName(context: Context, city: String): List<Address>? {
        return mLocationDomain.getLocationByCityName(context, city)
    }
}