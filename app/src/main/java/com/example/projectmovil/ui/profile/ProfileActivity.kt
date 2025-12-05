package com.example.projectmovil.ui.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projectmovil.R
import com.example.projectmovil.ui.auth.LoginActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val tvName = findViewById<TextView>(R.id.tv_profile_name)
        val tvEmail = findViewById<TextView>(R.id.tv_profile_email)
        val tvObjective = findViewById<TextView>(R.id.tv_profile_objective)
        val tvBmi = findViewById<TextView>(R.id.tv_profile_bmi)
        val btnLogout = findViewById<Button>(R.id.btn_logout)

        // De momento solo usamos el correo que viene del Home
        val userEmail = intent.getStringExtra("USER_EMAIL") ?: "sin-correo@ejemplo.com"
        val userName = userEmail.substringBefore("@").replaceFirstChar { it.uppercase() }

        tvName.text = userName
        tvEmail.text = userEmail
        tvObjective.text = "Objetivo: (pendiente de conectar con BD)"
        tvBmi.text = "IMC: -- (pendiente de conectar con BD)"

        btnLogout.setOnClickListener {
            // Aquí más adelante limpiaremos SharedPreferences / sesión
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
