package com.example.ejercicio_1

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejercicio_1.databinding.ActivityEjercicio5Binding
import kotlin.math.sqrt

class Ejercicio5Activity : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio5Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEjercicio5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Números Primos"

        binding.btnGenerar.setOnClickListener {
            generarPrimos()
        }
    }

    private fun generarPrimos() {
        val limiteStr = binding.etLimite.text.toString()
        if (limiteStr.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese un límite", Toast.LENGTH_SHORT).show()
            return
        }

        val limite = limiteStr.toIntOrNull()
        if (limite == null || limite < 2) {
            Toast.makeText(this, "Ingrese un número entero mayor o igual a 2", Toast.LENGTH_SHORT).show()
            return
        }

        if (limite > 10000) {
            Toast.makeText(this, "Por rendimiento, ingrese un número menor o igual a 10,000", Toast.LENGTH_SHORT).show()
            return
        }

        val primos = mutableListOf<Int>()
        for (i in 2..limite) {
            if (esPrimo(i)) {
                primos.add(i)
            }
        }

        binding.tvCantidadPrimos.text = "Primos hasta $limite (${primos.size} encontrados):"
        binding.tvListaPrimos.text = primos.joinToString(", ")
    }

    private fun esPrimo(n: Int): Boolean {
        if (n < 2) return false
        if (n == 2) return true
        if (n % 2 == 0) return false
        val limite = sqrt(n.toDouble()).toInt()
        for (i in 3..limite step 2) {
            if (n % i == 0) return false
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}