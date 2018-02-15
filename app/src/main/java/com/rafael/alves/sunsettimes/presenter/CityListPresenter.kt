package com.rafael.alves.sunsettimes.presenter

import android.location.Location
import com.rafael.alves.sunsettimes.domain.CityListDomain
import com.rafael.alves.sunsettimes.presenter.contract.SunsetTimesListener

class CityListPresenter {

    private var mCityListDomain = CityListDomain()

    fun getCurrentLocationSunsetTimes(location: Location?, listener: SunsetTimesListener) {
        mCityListDomain.getSunsetTimes(location?.latitude.toString(), location?.longitude.toString(), listener)
    }
}