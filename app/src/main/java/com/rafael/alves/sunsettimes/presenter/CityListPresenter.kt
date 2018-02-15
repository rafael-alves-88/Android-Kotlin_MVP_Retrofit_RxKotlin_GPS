package com.rafael.alves.sunsettimes.presenter

import android.location.Location
import com.rafael.alves.sunsettimes.domain.CityListDomain
import com.rafael.alves.sunsettimes.model.AddressCode
import com.rafael.alves.sunsettimes.model.CityListItem
import com.rafael.alves.sunsettimes.model.SunsetTimes
import com.rafael.alves.sunsettimes.presenter.contract.SunsetTimesListener

class CityListPresenter {

    private var mCityListDomain = CityListDomain()

    fun getCurrentLocationSunsetTimes(location: Location?, listener: SunsetTimesListener) {
        mCityListDomain.getSunsetTimes(location?.latitude.toString(), location?.longitude.toString(), listener)
    }

    fun getCityListToAdapter(sunsetTimes: SunsetTimes?, location: Location, address: AddressCode?, updateTime: String) : List<CityListItem> {
        val cityList = ArrayList<CityListItem>()

        val currentCity = CityListItem()
        currentCity.location = address?.address
        currentCity.countryCode = address?.countryCode
        currentCity.sunrise = sunsetTimes?.results?.sunrise
        currentCity.sunset = sunsetTimes?.results?.sunset
        currentCity.latitude = location.latitude.toString()
        currentCity.longitude = location.longitude.toString()
        currentCity.updateTime = updateTime
        cityList.add(currentCity)
        cityList.add(currentCity)
        cityList.add(currentCity)
        cityList.add(currentCity)
        cityList.add(currentCity)
        cityList.add(currentCity)
        cityList.add(currentCity)
        cityList.add(currentCity)

        // TODO - get cities from favorites

        return cityList
    }
}