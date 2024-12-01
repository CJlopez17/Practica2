package com.example.practica2

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class podometro : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var stepCounterSensor: Sensor? = null
    private var totalSteps = 0f
    private var previousTotalSteps = 0f
    private var isCounting = false

    private lateinit var stepsTextView: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_podometro)

        // Inicializa el SensorManager
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        // Verifica si el sensor de pasos está disponible
        if (stepCounterSensor == null) {
            Toast.makeText(this, "El sensor de pasos no está disponible", Toast.LENGTH_SHORT).show()
            return
        }

        // Verifica y solicita permisos
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACTIVITY_RECOGNITION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACTIVITY_RECOGNITION),
                    100
                )
            }
        }

        // Configura los elementos de la interfaz
        stepsTextView = findViewById(R.id.pasosTx)
        startButton = findViewById(R.id.startBt)
        stopButton = findViewById(R.id.stopBt)

        startButton.setOnClickListener {
            startCountingSteps()
        }

        stopButton.setOnClickListener {
            stopCountingSteps()
        }

        backHome()
    }

    private fun startCountingSteps() {
        if (!isCounting) {
            isCounting = true
            sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_UI)
            Toast.makeText(this, "Conteo de pasos iniciado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun stopCountingSteps() {
        if (isCounting) {
            isCounting = false
            sensorManager.unregisterListener(this)
            totalSteps = 0f
            previousTotalSteps = 0f
            stepsTextView.text = "Pasos: 0"
            Toast.makeText(this, "Conteo de pasos detenido", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_STEP_COUNTER) {
            if (previousTotalSteps == 0f) {
                previousTotalSteps = event.values[0]
            }
            val currentSteps = event.values[0] - previousTotalSteps
            totalSteps = currentSteps


            stepsTextView.text = "Pasos: ${totalSteps.toInt()}"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        if (isCounting) {
            sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.isNotEmpty() && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permiso denegado para acceder a actividad física", Toast.LENGTH_SHORT).show()
        }
    }

    fun backHome(){
        val btn = findViewById<Button>(R.id.backHmBt)
        btn.setOnClickListener{
            val saltar = Intent(this, MainActivity::class.java)
            startActivity(saltar)
        }
    }
}