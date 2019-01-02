package com.learning.cousiyvan.weatherapp.extensions

import android.content.Context

public fun Context.color(res: Int): Int =
    android.support.v4.content.ContextCompat.getColor(this, res)