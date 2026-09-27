package com.example.ejercicio_1

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejercicio_1.databinding.ActivityEjercicio6Binding
import kotlin.random.Random

class Ejercicio6Activity : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio6Binding
    private var numeroSecreto = 0
    private var intentos = 0
    private var juegoTerminado = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEjercicio6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Adivina el Número"

        iniciarJuego()

        binding.btnAdivinar.setOnClickListener {
            adivinar()
        }

        binding.btnNuevoJuego.setOnClickListener {
            iniciarJuego()
        }
    }

    private fun iniciarJuego() {
        numeroSecreto = Random.nextInt(1, 101)
        intentos = 0
        juegoTerminado = false
        binding.etIntento.setText("")
        binding.tvIntentosCount.text = "Intentos realizados: 0"
        binding.tvPista.text = "¡Ingresa un número y presiona Adivinar!"
        binding.tvPista.setTextColor(Color.BLACK)
        binding.btnAdivinar.isEnabled = true
    }

    private fun adivinar() {
        if (juegoTerminado) {
            Toast.makeText(this, "El juego ha terminado. Presiona 'Nuevo Juego'", Toast.LENGTH_SHORT).show()
            return
        }

        val intentoStr = binding.etIntento.text.toString()
        if (intentoStr.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese un número", Toast.LENGTH_SHORT).show()
            return
        }

        val intento = intentoStr.toIntOrNull()
        if (intento == null || intento !in 1..100) {
            Toast.makeText(this, "Ingrese un número entre 1 y 100", Toast.LENGTH_SHORT).show()
            return
        }

        intentos++
        binding.tvIntentosCount.text = "Intentos realizados: $intentos"

        when {
            intento < numeroSecreto -> {
                binding.tvPista.text = "El número secreto es MAYOR ↑"
                binding.tvPista.setTextColor(Color.parseColor("#E65100"))
            }
            intento > numeroSecreto -> {
                binding.tvPista.text = "El número secreto es MENOR ↓"
                binding.tvPista.setTextColor(Color.parseColor("#1565C0"))
            }
            else -> {
                juegoTerminado = true
                binding.tvPista.text = "¡Felicidades! 🎉 Adivinaste el número $numeroSecreto en $intentos intentos."
                binding.tvPista.setTextColor(Color.parseColor("#2E7D32"))
                binding.btnAdivinar.isEnabled = false
            }
        }
        binding.etIntento.setText("")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}