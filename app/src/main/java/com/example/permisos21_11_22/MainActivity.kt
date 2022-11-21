package com.example.permisos21_11_22

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.permisos21_11_22.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        binding.btnCamara.setOnClickListener {
            checkPermisos()
        }
    }

    private fun checkPermisos() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //No tenemos los permisos asi que hay que pedirlos
            pedirPermisosCamara()
        } else {
            abrirCamara()
        }
    }
    //---------------------------------------------------------------------------------------------
    private fun pedirPermisosCamara() {
        //Hay dos posibilidades, o bien ya me ha pedido los permisos y los he denegado
        //O es la primera vez y no los ha pedido
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            //Ya los he rechazado damos una explicacion
            Toast.makeText(this, "Para poder usar la aplicacion al completo, vete a ajustes y dame los permisos necesarios", Toast.LENGTH_SHORT).show()
        } else {
            //Es la primera vez que la pedimos, asi que los pedimos
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1000)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1000) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                abrirCamara()
            } else {
                Toast.makeText(this, "Se han rechazado los permisos la primera vez", Toast.LENGTH_SHORT).show()
            }
        }
    }
    //---------------------------------------------------------------------------------------------
    private fun abrirCamara() {
        Toast.makeText(this, "Abriendo Cámara....", Toast.LENGTH_SHORT).show()
    }
}