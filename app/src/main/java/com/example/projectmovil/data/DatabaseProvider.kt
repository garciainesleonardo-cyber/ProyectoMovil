package com.example.projectmovil.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projectmovil.Usuario // O FichaTecnica
import com.example.projectmovil.dao.UsuarioDao // O FichaTecnicaDao

@Database(entities = [Usuario::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao // O fichaTecnicaDao()

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "nombre_de_tu_db" // El nombre de tu base de datos
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}