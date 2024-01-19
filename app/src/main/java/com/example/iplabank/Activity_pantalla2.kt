package com.example.iplabank



import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.iplabank.data.Solicitud
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors






class ActivityPantalla2 : AppCompatActivity() {



    private var imageCapture: ImageCapture? = null
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla2)

        val previewView: PreviewView = findViewById(R.id.previewView)
        val preview = Preview.Builder().build().also {
            it.setSurfaceProvider(previewView.surfaceProvider)
        }

        if (allPermissionsGranted()) {
            startCamera(previewView)


        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
            cameraExecutor = Executors.newSingleThreadExecutor()
        }

        outputDirectory = getOutputDirectory()

        cameraExecutor = Executors.newSingleThreadExecutor()

        val btnCedulaFrontal: Button = findViewById(R.id.btnCedulaFrontal)
        btnCedulaFrontal.setOnClickListener {
            takePhoto("CedulaFrontal")
        }

        val btnCedulaTrasera: Button = findViewById(R.id.btnCedulaTrasera)
        btnCedulaTrasera.setOnClickListener {
            takePhoto("CedulaTrasera")
        }

        val btnSolicitar: Button = findViewById(R.id.btnSolicitar)
        btnSolicitar.setOnClickListener {
            val nuevaSolicitud = Solicitud(
                nombreCompleto = "John Doe",
                rut = "12345678-9",
                fechaNacimiento = "27/11/1982",
                email = "john.doe@example.com",
                telefono = "123456789",
                latitud = 0.0,
                longitud = 0.0,
                imagenCedulaFrente = "",
                imagenCedulaTrasera = "",
                fechaCreacion = ""
            )


            fun procesarSolicitud(solicitud: Solicitud) {

                println("Procesando solicitud: $solicitud")
            }
        }
    }

    private fun takePhoto(type: String) {
        val imageCapture = imageCapture ?: return

        val photoFile = File(
            outputDirectory,
            "${SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis())}_$type.jpg"
        )

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOptions, ContextCompat.getMainExecutor(this), object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e(TAG, "Error al guardar la imagen: ${exc.message}", exc)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val savedUri = output.savedUri ?: Uri.fromFile(photoFile)
                    Toast.makeText(baseContext, "Imagen guardada: $savedUri", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun startCamera(previewView: PreviewView) {

        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(Runnable{
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }


            imageCapture = ImageCapture.Builder().build()

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )

            } catch (exc: Exception) {
                Log.e(TAG, "Error al inicializar la cámara: ${exc.message}", exc)
            }

        }, ContextCompat.getMainExecutor(this))
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }



    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    companion object {
        private const val TAG = "CameraX"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)

    }
}
