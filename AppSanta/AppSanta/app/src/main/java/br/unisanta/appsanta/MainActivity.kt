package br.unisanta.appsanta

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.unisanta.appsanta.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class MainActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val email =  binding.edtName.text.toString()
        binding.btnLogin.setOnClickListener {
            auth = Firebase.auth
            val email =  binding.edtName.text.toString()
            val senha =  binding.edtPrice.text.toString()
            auth.signInWithEmailAndPassword(email,senha)
                .addOnCompleteListener(this){task ->
                    if(task.isSuccessful){
                        if(email != "lo214377@alunos.unisanta.br"){
                            val intent = Intent(this, MainActivity3::class.java)
                            intent.putExtra("USER_EMAIL", email)
                            startActivity(intent)
                        }else{
                            val intent = Intent(this, MainActivity2::class.java)
                            intent.putExtra("USER_EMAIL", email)
                            startActivity(intent)
                        }

                    }else{
                        Toast.makeText(this, "Não Logou", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        binding.btnCadastro.setOnClickListener{
            val intent = Intent(this, MainActivity1::class.java)
            intent.putExtra("USER_EMAIL", email)
            startActivity(intent)

        }

        binding.fbTela.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}