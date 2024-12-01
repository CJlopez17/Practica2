package com.example.practica2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Formilario : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_formilario)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        backHome()
        layoutform()
        cancelBt()
        acceptBt()
    }

    fun backHome(){
        val btn = findViewById<Button>(R.id.BackBt)
        btn.setOnClickListener{
            val saltar = Intent(this, MainActivity::class.java)
            startActivity(saltar)
        }
    }

    fun acceptBt(){
        val btn = findViewById<Button>(R.id.btnAccept)
        btn.setOnClickListener{
            val saltar = Intent(this, MainActivity::class.java)
            startActivity(saltar)
        }
    }

    fun cancelBt(){
        val btn = findViewById<Button>(R.id.btnCancel)
        btn.setOnClickListener{
            val saltar = Intent(this, MainActivity::class.java)
            startActivity(saltar)
        }
    }

    fun layoutform(){
        val btn_gps = findViewById<Button>(R.id.btnAccept)
        val tv_name = findViewById<TextView>(R.id.etName)
        btn_gps.setOnClickListener(){
            val message = "Nombre: ${tv_name.text}, es Estudiante"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}