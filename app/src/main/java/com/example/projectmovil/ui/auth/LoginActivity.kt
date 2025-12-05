package com.example.projectmovil.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectmovil.ui.main.MainActivity
import com.example.projectmovil.R
import com.example.projectmovil.data.dao.UsuarioDao
import com.example.projectmovil.data.database.AppDatabase
import com.example.projectmovil.ui.home.HomeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private lateinit var usuarioDao: UsuarioDao
    private val scope = CoroutineScope(Dispatchers.Main + Job())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // El crash PUEDE ocurrir aquí si el activity_login.xml no existe o está dañado.
        try {
            setContentView(R.layout.activity_login)
        } catch (e: Exception) {
            Log.e("LoginActivity", "Error al cargar el layout: ${e.message}")
            Toast.makeText(this, "ERROR: No se pudo cargar la interfaz de Login.", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        // 1. Inicialización de Room
        try {
            usuarioDao = AppDatabase.Companion.getDatabase(applicationContext).usuarioDao()
        } catch (e: Exception) {
            Log.e("LoginActivity", "Error al inicializar Room: ${e.message}")
            Toast.makeText(this, "ERROR: Problema con la Base de Datos. (Versión/Sintaxis)", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        // 2. Referencias a las vistas con manejo de errores (IDs)
        // Si el crash ocurre en una de estas líneas, el ID es incorrecto en activity_login.xml
        val etEmail: EditText = findViewById(R.id.et_login_email)
        val etPassword: EditText = findViewById(R.id.et_login_password)
        val btnLogin: Button = findViewById(R.id.btn_login)
        val btnCancel: Button = findViewById(R.id.btn_cancel_login)


        // --- Listener para Iniciar Sesión ---
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Ingresa correo y contraseña.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 3. Ejecución Asíncrona de BD
            scope.launch(Dispatchers.IO) {
                try {
                    val usuarioEncontrado = usuarioDao.login(email, password)

                    withContext(Dispatchers.Main) {
                        if (usuarioEncontrado != null) {
                            Toast.makeText(
                                this@LoginActivity,
                                "¡Bienvenido, ${usuarioEncontrado.nombre}!",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Credenciales incorrectas.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                } catch (e: Exception) {
                    Log.e("LoginActivity", "Error en la consulta de Login: ${e.message}")
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@LoginActivity,
                            "ERROR: Falló la comunicación con la BD.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

        // --- Listener para el botón Cancelar ---
        btnCancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}