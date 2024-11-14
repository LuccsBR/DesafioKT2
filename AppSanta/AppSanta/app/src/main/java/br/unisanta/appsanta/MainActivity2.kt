package br.unisanta.appsanta

import ProdutosDao
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.unisanta.appsanta.adapter.ProdutosAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity2 : AppCompatActivity(R.layout.activity_main2) {

    val dao = ProdutosDao()  // Criação do DAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fabVolta = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val rvProduto = findViewById<RecyclerView>(R.id.rc_Produto)

        rvProduto.layoutManager = LinearLayoutManager(this)

        dao.obterLista { produtos ->
            val adapter = ProdutosAdapter(produtos, dao)
            rvProduto.adapter = adapter
        }

        fabVolta.setOnClickListener {
            finish()
        }
    }
}
