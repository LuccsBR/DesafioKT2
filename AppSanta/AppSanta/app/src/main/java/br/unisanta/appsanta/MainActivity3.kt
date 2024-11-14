package br.unisanta.appsanta

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.unisanta.appsanta.databinding.ActivityMain3Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity3 : AppCompatActivity(R.layout.activity_main3) {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMain3Binding
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth and Firestore
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Set up the button click listener
        binding.btnEnviar.setOnClickListener {
            val email = intent.getStringExtra("USER_EMAIL")
            val pizza = binding.edtPrice2.text.toString().toInt()
            val crepe = binding.edtPrice5.text.toString().toInt()
            val hamburguer = binding.edtPrice4.text.toString().toInt()


            if (pizza!= null && crepe!= null && hamburguer != null ) {
                val product = hashMapOf(
                    "email" to email,
                    "pizza" to pizza,
                    "crepe" to crepe,
                    "hamburguer" to hamburguer,
                    "status" to "Pendente"

                    )

                // Add product to Firestore
                db.collection("produtos").add(product)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Adicionado", Toast.LENGTH_SHORT).show()
                        binding.edtPrice2.text.clear()
                        binding.edtPrice5.text.clear()
                        binding.edtPrice4.text.clear()

                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Erro ao adicionar", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
