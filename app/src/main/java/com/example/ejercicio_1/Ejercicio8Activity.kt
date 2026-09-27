package com.example.ejercicio_1

import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejercicio_1.databinding.ActivityEjercicio8Binding
import java.util.Locale

class Ejercicio8Activity : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio8Binding

    private val monedas = arrayOf("USD", "EUR", "GBP", "JPY", "MXN")

    private val tasaUSD = 1.0
    private val tasaEUR = 0.92
    private val tasaGBP = 0.79
    private val tasaJPY = 155.0
    private val tasaMXN = 18.20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEjercicio8Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Conversor de Monedas"

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, monedas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spMonedaOrigen.adapter = adapter

        binding.btnConvertir.setOnClickListener {
            convertir()
        }

        binding.btnLimpiar.setOnClickListener {
            limpiarCampos()
        }

        binding.btnTasas.setOnClickListener {
            mostrarTasasInfo()
        }

        binding.btnEjemplo.setOnClickListener {
            binding.etMonto.setText("100")
            binding.spMonedaOrigen.setSelection(0)
            convertir()
        }
    }

    private fun convertir() {
        val montoStr = binding.etMonto.text.toString()
        if (montoStr.isEmpty()) {
            Toast.makeText(this, "Ingrese un monto a convertir", Toast.LENGTH_SHORT).show()
            return
        }

        val monto = montoStr.toDoubleOrNull()
        if (monto == null || monto < 0) {
            Toast.makeText(this, "Monto inválido", Toast.LENGTH_SHORT).show()
            return
        }

        val posOrigen = binding.spMonedaOrigen.selectedItemPosition

        val montoEnUSD = when (posOrigen) {
            0 -> monto / tasaUSD
            1 -> monto / tasaEUR
            2 -> monto / tasaGBP
            3 -> monto / tasaJPY
            4 -> monto / tasaMXN
            else -> monto
        }

        val valorUSD = montoEnUSD * tasaUSD
        val valorEUR = montoEnUSD * tasaEUR
        val valorGBP = montoEnUSD * tasaGBP
        val valorJPY = montoEnUSD * tasaJPY
        val valorMXN = montoEnUSD * tasaMXN

        binding.etUSD.setText(String.format(Locale.getDefault(), "$ %.2f", valorUSD))
        binding.etEUR.setText(String.format(Locale.getDefault(), "€ %.2f", valorEUR))
        binding.etGBP.setText(String.format(Locale.getDefault(), "£ %.2f", valorGBP))
        binding.etJPY.setText(String.format(Locale.getDefault(), "¥ %.2f", valorJPY))
        binding.etMXN.setText(String.format(Locale.getDefault(), "$ %.2f", valorMXN))
    }

    private fun limpiarCampos() {
        binding.etMonto.setText("")
        binding.etUSD.setText("")
        binding.etEUR.setText("")
        binding.etGBP.setText("")
        binding.etJPY.setText("")
        binding.etMXN.setText("")
    }

    private fun mostrarTasasInfo() {
        val msg = """
            Tasas de cambio aplicadas (Base 1 USD):
            
            • 1.00 USD = $tasaUSD USD (Dólar)
            • 1.00 USD = $tasaEUR EUR (Euro)
            • 1.00 USD = $tasaGBP GBP (Libra)
            • 1.00 USD = $tasaJPY JPY (Yen)
            • 1.00 USD = $tasaMXN MXN (Peso)
        """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle("Tasas de Cambio de Referencia")
            .setMessage(msg)
            .setPositiveButton("Aceptar", null)
            .show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}