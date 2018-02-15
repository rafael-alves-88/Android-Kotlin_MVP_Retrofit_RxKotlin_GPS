package com.rafael.alves.sunsettimes.utils

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

class PlayServicesUtils {

    companion object {
        fun isGooglePlayServicesAvailable(context: Context): Boolean {
            val result: Boolean

            val googleApiAvailability = GoogleApiAvailability.getInstance()
            val statusCode = googleApiAvailability.isGooglePlayServicesAvailable(context)
            result = statusCode == ConnectionResult.SUCCESS

            return result
        }
    }
}