package com.example.practica2

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth


class login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        auth = Firebase.auth
        var txtEmail=findViewById<EditText>(R.id.signup_email)
        var txtPassword=findViewById<EditText>(R.id.signup_password)
        var btnRegister=findViewById<Button>(R.id.signup_button)

        btnRegister.setOnClickListener(){
            val correo=txtEmail.text.toString()
            val clave=txtPassword.text.toString()
            auth.signInWithEmailAndPassword(correo, clave)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        Toast.makeText(
                            baseContext,
                            "Authentication is correct.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        updateUI(null)
                    }
                }

        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this, Formilario::class.java) // Cambia "HomeActivity" por la actividad deseada
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(
                this,
                "Por favor, verifica tus credenciales.",
                Toast.LENGTH_SHORT,
            ).show()
        }
    }

}