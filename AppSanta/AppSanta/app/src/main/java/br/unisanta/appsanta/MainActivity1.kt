package br.unisanta.appsanta

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.unisanta.appsanta.databinding.ActivityMain1Binding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity1 : AppCompatActivity(R.layout.activity_main1) {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMain1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        binding.btnSave.setOnClickListener{
            auth.createUserWithEmailAndPassword(binding.edtName.text.toString(),binding.edtPrice.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Criou", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Não Criou", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }
}
