package com.example.ejercicio_1

import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejercicio_1.databinding.ActivityEjercicio7Binding
import java.util.Locale

class Ejercicio7Activity : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio7Binding

    private val frecuencias = arrayOf("Anual (1 vez/año)", "Semestral (2 veces/año)", "Trimestral (4 veces/año)", "Mensual (12 veces/año)")
    private val periodosPorAnio = arrayOf(1, 2, 4, 12)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEjercicio7Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Interés Compuesto"

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, frecuencias)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spCapitalizacion.adapter = adapter

        binding.btnCalcular.setOnClickListener {
            calcularInteresCompuesto()
        }
    }

    private fun calcularInteresCompuesto() {
        val capitalStr = binding.etCapital.text.toString()
        val tasaStr = binding.etTasa.text.toString()
        val aniosStr = binding.etAnios.text.toString()

        if (capitalStr.isEmpty() || tasaStr.isEmpty() || aniosStr.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val P = capitalStr.toDoubleOrNull()
        val rPct = tasaStr.toDoubleOrNull()
        val tYears = aniosStr.toIntOrNull()

        if (P == null || P <= 0 || rPct == null || rPct < 0 || tYears == null || tYears <= 0) {
            Toast.makeText(this, "Ingrese valores numéricos válidos", Toast.LENGTH_SHORT).show()
            return
        }

        val posFreq = binding.spCapitalizacion.selectedItemPosition
        val n = periodosPorAnio[posFreq]
        val tasaPeriodica = (rPct / 100.0) / n
        val totalPeriodos = n * tYears

        var montoActual = P
        val sb = StringBuilder()

        for (p in 1..totalPeriodos) {
            val interesPeriodo = montoActual * tasaPeriodica
            montoActual += interesPeriodo
            val anio = ((p - 1) / n) + 1
            val subPeriodo = ((p - 1) % n) + 1
            sb.append(String.format(Locale.getDefault(), "Período %d (Año %d, Sub-período %d): $%.2f\n", p, anio, subPeriodo, montoActual))
        }

        val interesTotal = montoActual - P

        binding.tvMontoFinal.text = String.format(Locale.getDefault(), "Monto Final: $%.2f", montoActual)
        binding.tvInteresGenerado.text = String.format(Locale.getDefault(), "Interés Generado: $%.2f", interesTotal)
        binding.tvDetallePeriodos.text = sb.toString().trimEnd()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}