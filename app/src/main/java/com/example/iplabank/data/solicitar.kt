package com.example.iplabank.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "solicitudes")
data class Solicitud(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nombreCompleto: String,
    val rut: String,
    val fechaNacimiento: String,
    val email: String,
    val telefono: String,
    val latitud: Double,
    val longitud: Double,
    val imagenCedulaFrente: String,
    val imagenCedulaTrasera: String,
    val fechaCreacion: String ,
)