package com.example.ejercicio_1

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejercicio_1.databinding.ActivityEjercicio4Binding
import java.util.Locale

class Ejercicio4Activity : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEjercicio4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Calculadora de IMC"

        binding.btnCalcular.setOnClickListener {
            calcularIMC()
        }
    }

    private fun calcularIMC() {
        val pesoStr = binding.etPeso.text.toString()
        val alturaStr = binding.etAltura.text.toString()

        if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese peso y altura", Toast.LENGTH_SHORT).show()
            return
        }

        val peso = pesoStr.toDoubleOrNull()
        var altura = alturaStr.toDoubleOrNull()

        if (peso == null || peso <= 0 || altura == null || altura <= 0) {
            Toast.makeText(this, "Ingrese valores válidos mayores a cero", Toast.LENGTH_SHORT).show()
            return
        }

        if (altura > 10.0) {
            altura /= 100.0
        }

        val imc = peso / (altura * altura)

        val (categoria, color) = when {
            imc < 18.5 -> Pair("Bajo peso", Color.parseColor("#388E3C"))
            imc < 25.0 -> Pair("Peso normal (Saludable)", Color.parseColor("#2E7D32"))
            imc < 30.0 -> Pair("Sobrepeso", Color.parseColor("#F57C00"))
            imc < 35.0 -> Pair("Obesidad Grado I", Color.parseColor("#E65100"))
            imc < 40.0 -> Pair("Obesidad Grado II", Color.parseColor("#D32F2F"))
            else -> Pair("Obesidad Grado III (Mórbida)", Color.parseColor("#B71C1C"))
        }

        binding.tvImcValor.text = String.format(Locale.getDefault(), "IMC: %.2f", imc)
        binding.tvImcCategoria.text = "Categoría: $categoria"
        binding.tvImcCategoria.setTextColor(color)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}