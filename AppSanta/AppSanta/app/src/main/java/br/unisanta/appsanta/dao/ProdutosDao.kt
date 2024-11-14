import android.util.Log
import br.unisanta.appsanta.model.Produtos
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore

class ProdutosDao {

    val db = Firebase.firestore

    fun obterLista(callback: (List<Produtos>) -> Unit) {
        val produtos = mutableListOf<Produtos>()

        db.collection("produtos")
            .get()
            .addOnSuccessListener { result ->
                Log.d("TAG", "Dados recebidos: ${result.size()} documentos")
                for (document in result) {
                    val produto = document.toObject(Produtos::class.java)
                    produto.id = document.id
                    produtos.add(produto)
                    Log.d("TAG", "Produto: $produto")
                }
                callback(produtos)
            }
            .addOnFailureListener { exception ->
                Log.w("Erro", "Erro ao recuperar dados do Firestore", exception)
                callback(emptyList())
            }
    }

    fun atualizarStatusPedido(id: String, novoStatus: String, callback: (Boolean) -> Unit) {
        val pedidoRef = db.collection("produtos").document(id)

        pedidoRef.update(mapOf(
            "status" to novoStatus,
            "dataModificacao" to FieldValue.serverTimestamp()
        ))
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { e ->
                Log.w("Erro", "Erro ao atualizar documento", e)
                callback(false)
            }
    }
}
