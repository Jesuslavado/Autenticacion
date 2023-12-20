package com.example.autenticacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.autenticacion.databinding.ActivityBienvenidaBinding
import com.google.firebase.auth.FirebaseAuth

class Bienvenida : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    val binding=ActivityBienvenidaBinding.inflate(layoutInflater)
    setContentView(binding.root)
        title="Bienvenida"
        val extras=intent.extras
        val nombre=extras?.getString("NombreUsuario")
        binding.textView.text="Bienvenido, $nombre"

        binding.bcerra.setOnClickListener{
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}