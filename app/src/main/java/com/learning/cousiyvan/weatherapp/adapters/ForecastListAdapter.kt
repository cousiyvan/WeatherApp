package com.learning.cousiyvan.weatherapp.adapters

import android.support.constraint.R.id.parent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.learning.cousiyvan.weatherapp.R
import com.learning.cousiyvan.weatherapp.domain.model.Forecast
import com.learning.cousiyvan.weatherapp.domain.model.ForecastList
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*
import org.jetbrains.anko.find

class ForecastListAdapter(private val weekForcast: ForecastList, private val itemClick: (Forecast) -> Unit) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
      return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForcast[position])
    }

    override fun getItemCount(): Int = weekForcast.size

    // class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
    class ViewHolder(view: View, private val itemClick: (Forecast) -> Unit): RecyclerView.ViewHolder(view) {
        private val iconView = view.find<ImageView>(R.id.icon)
        private val dateView = view.find<TextView>(R.id.date)
        private val descriptionView = view.find<TextView>(R.id.description)
        private val maxTemperatureView = view.find<TextView>(R.id.maxTemperature)
        private val minTemperatureView = view.find<TextView>(R.id.minTemperature)

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.context).load(iconUrl).into(iconView)
                dateView.text = date
                descriptionView.text = description
                maxTemperatureView.text = "$high"
                minTemperatureView.text = "$low"
                itemView.setOnClickListener() { itemClick(this)}
            }
        }
    }

    interface OnItemClickListener {
        operator fun invoke(forecast: Forecast)
    }
}
