package com.example.ejercicio_1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejercicio_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnEjercicio1.setOnClickListener {
            startActivity(Intent(this, Ejercicio1Activity::class.java))
        }
        binding.btnEjercicio2.setOnClickListener {
            startActivity(Intent(this, Ejercicio2Activity::class.java))
        }
        binding.btnEjercicio3.setOnClickListener {
            startActivity(Intent(this, Ejercicio3Activity::class.java))
        }
        binding.btnEjercicio4.setOnClickListener {
            startActivity(Intent(this, Ejercicio4Activity::class.java))
        }
        binding.btnEjercicio5.setOnClickListener {
            startActivity(Intent(this, Ejercicio5Activity::class.java))
        }
        binding.btnEjercicio6.setOnClickListener {
            startActivity(Intent(this, Ejercicio6Activity::class.java))
        }
        binding.btnEjercicio7.setOnClickListener {
            startActivity(Intent(this, Ejercicio7Activity::class.java))
        }
        binding.btnEjercicio8.setOnClickListener {
            startActivity(Intent(this, Ejercicio8Activity::class.java))
        }
    }
}