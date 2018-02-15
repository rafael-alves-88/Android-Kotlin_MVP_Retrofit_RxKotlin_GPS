package com.rafael.alves.sunsettimes.utils

import android.content.Context
import android.widget.ImageView
import com.rafael.alves.sunsettimes.BuildConfig
import com.squareup.picasso.Picasso

class ImageUtils {

    companion object {
        fun loadCountryFlag(context: Context, countryCode: String?, imageView: ImageView) {
            Picasso.with(context).load(String.format(BuildConfig.COUNTRY_FLAG_BASE_URL, countryCode)).into(imageView)
        }
    }
}