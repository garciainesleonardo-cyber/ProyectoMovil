package com.example.projectmovil.ui.profile

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projectmovil.R
import com.example.projectmovil.ui.auth.LoginActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val btnLogout = findViewById<TextView>(R.id.btn_logout)
        val btnDelete = findViewById<TextView>(R.id.btn_delete_account)
        val btnEdit = findViewById<TextView>(R.id.btn_edit_profile)

        btnLogout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        btnDelete.setOnClickListener {
            // Más adelante: eliminar usuario desde ROOM
        }

        btnEdit.setOnClickListener {
            // Más adelante: Activity para editar perfil
        }
    }
}
