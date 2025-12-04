package com.example.projectmovil.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projectmovil.data.entity.UsuarioEntity

@Dao
interface UsuarioDao {

    /**
     * FUNCIÓN CLAVE PARA EL LOGIN
     * Busca un usuario que coincida con el email y la contraseña.
     */
    @Query("SELECT * FROM usuarios WHERE email = :email AND contrasena = :contrasena LIMIT 1")
    suspend fun login(email: String, contrasena: String): UsuarioEntity?

    /**
     * Función para registrar un nuevo usuario (usada también para la prueba en LoginActivity)
     */
    @Insert
    suspend fun registrar(usuario: UsuarioEntity)
}