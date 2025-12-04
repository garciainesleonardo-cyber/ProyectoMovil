package com.example.projectmovil.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projectmovil.data.entity.FichaTecnicaEntity
import kotlinx.coroutines.flow.Flow

// La anotación @Dao le indica a Room que esta es una interfaz de acceso a datos.
@Dao
interface FichaTecnicaDao {

    // @Insert define una función para insertar datos.
    // suspend se usa para que pueda ser llamada desde una corrutina sin bloquear el hilo principal.
    @Insert
    suspend fun insertar(ficha: FichaTecnicaEntity)

    // @Query permite escribir consultas SQL personalizadas.
    // Esta función obtiene todas las fichas y las devuelve como un Flow,
    // lo que permite que la UI se actualice automáticamente cuando los datos cambien.
    @Query("SELECT * FROM fichas_tecnicas ORDER BY nombreProducto ASC")
    fun obtenerTodas(): Flow<List<FichaTecnicaEntity>>
}