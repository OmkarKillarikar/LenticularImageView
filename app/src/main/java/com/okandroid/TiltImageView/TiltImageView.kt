package com.okandroid.TiltImageView

import android.content.Context
import android.graphics.Bitmap
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class TiltImageView(context: Context, attrs: AttributeSet) : AppCompatImageView(context, attrs) {


    private val sensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

        override fun onSensorChanged(event: SensorEvent?) {
            when (event?.sensor?.type) {
                Sensor.TYPE_ACCELEROMETER -> {
                    val xVal = event.values[0]
                    for (i in slots.indices) {
                        when (xVal) {
                            in slots[i].first..slots[i].second -> setImageBitmap(bitmaps?.get(i))
                        }
                    }
                }
            }
        }
    }

    init {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager?
        sensorManager?.registerListener(
            sensorEventListener,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_UI
        )
    }

    private var maxTilt = 5F
    private var slotPerImage = 0F
    private var slots = ArrayList<Pair<Float, Float>>()

    var bitmaps: ArrayList<Bitmap>? = null
        set(value) {
            if (value?.size != null && value.isNotEmpty()) {
                slotPerImage = (maxTilt * 2F) / (value.size - 1F)
                var currentVal = maxTilt * -1
                while (true) {
                    val slotEnd = currentVal + slotPerImage
                    slots.add(Pair(currentVal, slotEnd))
                    if (slotEnd >= maxTilt) break
                    currentVal = slotEnd
                }
                invalidate()
            }
            field = value
        }
}