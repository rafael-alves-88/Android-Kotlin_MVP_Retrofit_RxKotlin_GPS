package com.rafael.alves.sunsettimes.view.adapter

import android.support.v7.widget.RecyclerView
import android.content.Context
import com.yayandroid.parallaxrecyclerview.ParallaxViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.rafael.alves.sunsettimes.R
import com.rafael.alves.sunsettimes.model.CityListItem
import com.rafael.alves.sunsettimes.utils.ImageUtils
import kotlinx.android.synthetic.main.city_list_row.view.*

class CityListAdapter(private var mContext: Context,
                      private var cityList: List<CityListItem>,
                      private var listener: OnCityClickListener)
    : RecyclerView.Adapter<CityListAdapter.ViewHolder>() {

    interface OnCityClickListener {
        fun onCityClick(cityListItem: CityListItem)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val inflater = LayoutInflater.from(mContext)
        return ViewHolder(inflater.inflate(R.layout.city_list_row, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val cityListItem = cityList[position]
        ImageUtils.loadCountryFlag(mContext, cityListItem.countryCode, viewHolder.backgroundImage)

        // Click
        viewHolder.bind(cityList[position], listener)

        // # CAUTION:
        // Important to call this method
        viewHolder.backgroundImage.reuse()
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    fun updateList(cityList: List<CityListItem>) {
        this.cityList = cityList
        this.notifyDataSetChanged()
    }

    fun addCityToList(city: CityListItem) {
        val array = ArrayList<CityListItem>()
        array.add(city)
        this.cityList = array
        this.notifyDataSetChanged()
    }

    /**
     * # CAUTION:
     * ViewHolder must extend from ParallaxViewHolder
     */
    class ViewHolder(v: View) : ParallaxViewHolder(v) {

        override fun getParallaxImageId(): Int {
            return R.id.ivCountryFlag
        }

        fun bind(cityListItem: CityListItem, listener: OnCityClickListener) {
            itemView.tvSunrise.text = cityListItem.sunrise
            itemView.tvSunset.text = cityListItem.sunset
            itemView.tvLatitude.text = cityListItem.latitude
            itemView.tvLongitude.text = cityListItem.longitude
            itemView.tvLastUpdate.text = cityListItem.updateTime
            itemView.tvLocation.text = cityListItem.location
            itemView.setOnClickListener { listener.onCityClick(cityListItem) }
        }
    }
}