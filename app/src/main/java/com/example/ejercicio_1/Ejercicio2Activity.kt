package com.example.ejercicio_1

import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejercicio_1.databinding.ActivityEjercicio2Binding
import java.util.Locale

class Ejercicio2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio2Binding

    private val opcionesConversion = arrayOf(
        "Celsius a Fahrenheit (°C ➔ °F)",
        "Fahrenheit a Celsius (°F ➔ °C)",
        "Celsius a Kelvin (°C ➔ K)",
        "Kelvin a Celsius (K ➔ °C)",
        "Fahrenheit a Kelvin (°F ➔ K)",
        "Kelvin a Fahrenheit (K ➔ °F)"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEjercicio2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Conversor de Temperatura"

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesConversion)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spTipoConversion.adapter = adapter

        binding.btnConvertir.setOnClickListener {
            convertir()
        }
    }

    private fun convertir() {
        val inputStr = binding.etValor.text.toString()
        if (inputStr.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese un valor de temperatura", Toast.LENGTH_SHORT).show()
            return
        }

        val valor = inputStr.toDoubleOrNull()
        if (valor == null) {
            Toast.makeText(this, "Número no válido", Toast.LENGTH_SHORT).show()
            return
        }

        val posicion = binding.spTipoConversion.selectedItemPosition
        var resultado = 0.0
        var unidad = ""

        when (posicion) {
            0 -> {
                resultado = (valor * 1.8) + 32
                unidad = "°F"
            }
            1 -> {
                resultado = (valor - 32) / 1.8
                unidad = "°C"
            }
            2 -> {
                resultado = valor + 273.15
                unidad = "K"
            }
            3 -> {
                resultado = valor - 273.15
                unidad = "°C"
            }
            4 -> {
                resultado = ((valor - 32) * 5.0 / 9.0) + 273.15
                unidad = "K"
            }
            5 -> {
                resultado = ((valor - 273.15) * 9.0 / 5.0) + 32
                unidad = "°F"
            }
        }

        binding.tvResultado.text = String.format(Locale.getDefault(), "%.2f %s", resultado, unidad)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}