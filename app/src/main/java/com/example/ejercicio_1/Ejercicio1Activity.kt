package com.example.ejercicio_1

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejercicio_1.databinding.ActivityEjercicio1Binding
import kotlin.math.PI

class Ejercicio1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEjercicio1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Área y Perímetro"

        setupRadioGroup()

        binding.btnCalcular.setOnClickListener {
            calcular()
        }
    }

    private fun setupRadioGroup() {
        binding.rgFiguras.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbCirculo -> {
                    binding.tilRadio.visibility = View.VISIBLE
                    binding.tilBase.visibility = View.GONE
                    binding.tilAltura.visibility = View.GONE
                    binding.tilLadoA.visibility = View.GONE
                    binding.tilLadoB.visibility = View.GONE
                    binding.tilLadoC.visibility = View.GONE
                }
                R.id.rbRectangulo -> {
                    binding.tilRadio.visibility = View.GONE
                    binding.tilBase.visibility = View.VISIBLE
                    binding.tilAltura.visibility = View.VISIBLE
                    binding.tilLadoA.visibility = View.GONE
                    binding.tilLadoB.visibility = View.GONE
                    binding.tilLadoC.visibility = View.GONE
                }
                R.id.rbTriangulo -> {
                    binding.tilRadio.visibility = View.GONE
                    binding.tilBase.visibility = View.VISIBLE
                    binding.tilAltura.visibility = View.VISIBLE
                    binding.tilLadoA.visibility = View.VISIBLE
                    binding.tilLadoB.visibility = View.VISIBLE
                    binding.tilLadoC.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun calcular() {
        when (binding.rgFiguras.checkedRadioButtonId) {
            R.id.rbCirculo -> {
                val radioStr = binding.etRadio.text.toString()
                if (radioStr.isEmpty()) {
                    Toast.makeText(this, "Por favor ingrese el radio", Toast.LENGTH_SHORT).show()
                    return
                }
                val r = radioStr.toDoubleOrNull() ?: 0.0
                val area = PI * r * r
                val perimetro = 2 * PI * r
                mostrarResultado(area, perimetro)
            }
            R.id.rbRectangulo -> {
                val baseStr = binding.etBase.text.toString()
                val alturaStr = binding.etAltura.text.toString()
                if (baseStr.isEmpty() || alturaStr.isEmpty()) {
                    Toast.makeText(this, "Ingrese base y altura", Toast.LENGTH_SHORT).show()
                    return
                }
                val base = baseStr.toDoubleOrNull() ?: 0.0
                val altura = alturaStr.toDoubleOrNull() ?: 0.0
                val area = base * altura
                val perimetro = 2 * (base + altura)
                mostrarResultado(area, perimetro)
            }
            R.id.rbTriangulo -> {
                val baseStr = binding.etBase.text.toString()
                val alturaStr = binding.etAltura.text.toString()
                val ladoAStr = binding.etLadoA.text.toString()
                val ladoBStr = binding.etLadoB.text.toString()
                val ladoCStr = binding.etLadoC.text.toString()

                if (baseStr.isEmpty() || alturaStr.isEmpty() || ladoAStr.isEmpty() || ladoBStr.isEmpty() || ladoCStr.isEmpty()) {
                    Toast.makeText(this, "Complete todos los campos del triángulo", Toast.LENGTH_SHORT).show()
                    return
                }
                val base = baseStr.toDoubleOrNull() ?: 0.0
                val altura = alturaStr.toDoubleOrNull() ?: 0.0
                val ladoA = ladoAStr.toDoubleOrNull() ?: 0.0
                val ladoB = ladoBStr.toDoubleOrNull() ?: 0.0
                val ladoC = ladoCStr.toDoubleOrNull() ?: 0.0

                val area = 0.5 * base * altura
                val perimetro = ladoA + ladoB + ladoC
                mostrarResultado(area, perimetro)
            }
        }
    }

    private fun mostrarResultado(area: Double, perimetro: Double) {
        binding.tvResultadoArea.text = String.format("Área: %.2f", area)
        binding.tvResultadoPerimetro.text = String.format("Perímetro: %.2f", perimetro)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}