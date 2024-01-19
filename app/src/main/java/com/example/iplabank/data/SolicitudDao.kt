package com.example.iplabank.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.iplabank.R



@Dao
interface SolicitudDao {

    @Query("SELECT * FROM solicitudes ORDER BY fechaCreacion DESC")
    fun getAllSolicitudes(): List<Solicitud>

    @Query("SELECT * FROM solicitudes WHERE id = :solicitudId")
    fun getSolicitudById(solicitudId: Long): Solicitud?

    @Insert
    suspend fun insertSolicitud(solicitud: Solicitud)

    @Delete
    suspend fun deleteSolicitud(solicitud: Solicitud)

    @Update
    suspend fun updateSolicitud(solicitud: Solicitud)
}