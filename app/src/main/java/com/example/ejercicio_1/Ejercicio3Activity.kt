package com.example.ejercicio_1

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejercicio_1.databinding.ActivityEjercicio3Binding

class Ejercicio3Activity : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEjercicio3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Tabla de Multiplicar"

        binding.btnGenerar.setOnClickListener {
            generarTabla()
        }
    }

    private fun generarTabla() {
        val numStr = binding.etNumero.text.toString()
        if (numStr.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese un número", Toast.LENGTH_SHORT).show()
            return
        }

        val num = numStr.toIntOrNull()
        if (num == null) {
            Toast.makeText(this, "Número no válido", Toast.LENGTH_SHORT).show()
            return
        }

        val sb = StringBuilder()
        for (i in 1..10) {
            val resultado = num * i
            sb.append("$num x $i = $resultado\n")
        }

        binding.tvTituloTabla.text = "Tabla de Multiplicar del $num:"
        binding.tvResultadoTabla.text = sb.toString().trimEnd()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}