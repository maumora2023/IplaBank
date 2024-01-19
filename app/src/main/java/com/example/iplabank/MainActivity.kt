package com.example.iplabank

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val requestLocationPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                getLocationAndOpenActivityPantalla2()
            } else {
                Toast.makeText(this, "Se requiere permiso de ubicación", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val btnSolicitar: Button = findViewById(R.id.solicitarCuenta)
        btnSolicitar.setOnClickListener {
            checkLocationPermission()
        }
    }

    private fun checkLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                getLocationAndOpenActivityPantalla2()
            }
            else -> {
                requestLocationPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun getLocationAndOpenActivityPantalla2() {
        val intent = Intent(this, ActivityPantalla2::class.java)
        intent.putExtra("nombreCompleto", "John Doe")
        intent.putExtra("rut", "12345678-9")
        startActivity(intent)
    }

}