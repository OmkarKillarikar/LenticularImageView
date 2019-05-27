package com.okandroid.TiltImageView

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), SensorEventListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bitmaps = ArrayList<Bitmap>()
        bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.image1))
        bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.image2))
        bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.image3))
        bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.image4))
        bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.image5))

        tilty.bitmaps = bitmaps

        setListeners()
    }


    private fun setListeners() {
        val sensorManager = this.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        sensorManager.let {
            it.registerListener(
                this, it.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL
            )
            it.registerListener(
                this, it.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_NORMAL
            )
            it.registerListener(
                this, it.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_NORMAL
            )
            it.registerListener(
                this, it.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR),
                SensorManager.SENSOR_DELAY_NORMAL
            )
            it.registerListener(
                this, it.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),
                SensorManager.SENSOR_DELAY_NORMAL
            )
            it.registerListener(
                this, it.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR),
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        val data = Arrays.toString(event?.values)
        when (event?.sensor?.type) {
            Sensor.TYPE_ACCELEROMETER -> tv1.text = "TYPE_ACCELEROMETER - $data"
            Sensor.TYPE_MAGNETIC_FIELD -> tv2.text = "TYPE_MAGNETIC_FIELD - $data"
            Sensor.TYPE_GYROSCOPE -> tv3.text = "TYPE_GYROSCOPE - $data"
            Sensor.TYPE_GAME_ROTATION_VECTOR -> tv4.text = "TYPE_GAME_ROTATION_VECTOR - $data"
            Sensor.TYPE_ROTATION_VECTOR -> tv5.text = "TYPE_ROTATION_VECTOR - $data"
            Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR -> tv6.text = "TYPE_GEOMAGNETIC_ROTATION_VECTOR - $data"
        }
    }
}
