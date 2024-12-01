package com.example.practica2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Practica2)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        navegarFm()
        navegarImg()
        navegarGPS()
        navegarPodometro()
        navegarCamara()
        navegarRegister()
        navegarLogin()
    }

    fun navegarFm(){
        val btn = findViewById<Button>(R.id.FormBt)
        btn.setOnClickListener{
            val saltar = Intent(this, Formilario::class.java)
            startActivity(saltar)
        }
    }

    fun navegarImg(){
        val btn = findViewById<Button>(R.id.ImgBt)
        btn.setOnClickListener{
            val saltar = Intent(this, Imagen::class.java)
            startActivity(saltar)
        }
    }

    fun navegarGPS(){
        val btn = findViewById<Button>(R.id.gpsBt)
        btn.setOnClickListener{
            val saltar = Intent(this, gps::class.java)
            startActivity(saltar)
        }
    }

    fun navegarPodometro(){
        val btn = findViewById<Button>(R.id.podoBt)
        btn.setOnClickListener{
            val saltar = Intent(this, podometro::class.java)
            startActivity(saltar)
        }
    }

    fun navegarCamara(){
        val btn = findViewById<Button>(R.id.cameraBt1)
        btn.setOnClickListener{
            val saltar = Intent(this, camara::class.java)
            startActivity(saltar)
        }
    }

    fun navegarRegister(){
        val btn = findViewById<Button>(R.id.registerBt)
        btn.setOnClickListener{
            val saltar = Intent(this, register::class.java)
            startActivity(saltar)
        }
    }

    fun navegarLogin(){
        val btn = findViewById<Button>(R.id.loginBt)
        btn.setOnClickListener{
            val saltar = Intent(this, login::class.java)
            startActivity(saltar)
        }
    }

}
