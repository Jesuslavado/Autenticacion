package com.example.autenticacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.autenticacion.databinding.ActivityBienvenidaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Bienvenida : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    val binding=ActivityBienvenidaBinding.inflate(layoutInflater)
    setContentView(binding.root)
        title="Bienvenida"
        val extras=intent.extras
        val nombre=extras?.getString("NombreUsuario")
        binding.textView.text="Bienvenido, $nombre"


        val db= FirebaseFirestore.getInstance()

        binding.bguardar.setOnClickListener {
            if(binding.matricula.text.isNotEmpty() &&
                binding.marca.text.isNotEmpty() &&
                binding.modelo.text.isNotEmpty() &&
                binding.color.text.isNotEmpty()){
                 db.collection("coches").add(mapOf(
                     "color" to binding.color.text,
                     "marca" to binding.marca.text,
                     "matricula" to binding.matricula.text,
                     "modelo" to binding.modelo.text
                 ))
                     .addOnSuccessListener { documento ->
                         Toast.makeText(this, "nuevo coche añadido con el id: ${documento.id}", Toast.LENGTH_LONG).show()
                     }
                     .addOnFailureListener{
                         Toast.makeText(this, "Error en la insercción de registro", Toast.LENGTH_LONG).show()
                     }
            }
            else{
                Toast.makeText(this, "Algun campo esta vacío", Toast.LENGTH_LONG).show()
            }
        }

        binding.bcerra.setOnClickListener{
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}