package com.example.iplabank.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Dao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Dao
class SolicitarViewModel(application: Application) : AndroidViewModel(application) {
    private val solicitudDao: SolicitudDao
    private val baseDatos: BaseDatos

    init {
        baseDatos = BaseDatos.getInstance(application)
        solicitudDao = baseDatos.solicitarDao()
    }
    fun guardarSolicitud(solicitud: Solicitud) {
        viewModelScope.launch(Dispatchers.IO) {
            solicitudDao.insertSolicitud(solicitud)
        }
    }

}