// Ruta: com.example.projectmovil.data.database.AppDatabase.kt
package com.example.projectmovil.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projectmovil.data.dao.UsuarioDao
import com.example.projectmovil.data.entity.UsuarioEntity

// Versión 1, solo con la entidad UsuarioEntity
@Database(entities = [UsuarioEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "project_movil_db"
                )
                    // Importante para el desarrollo: permite actualizar la estructura sin migrar.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}