package br.unisanta.appsanta.adapter

import ProdutosDao
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.unisanta.appsanta.R
import br.unisanta.appsanta.model.Produtos

class ProdutosAdapter(
    private val produtos: List<Produtos>,
    private val dao: ProdutosDao
) : RecyclerView.Adapter<ProdutosAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtNome: TextView = itemView.findViewById(R.id.txtNome)
        val txtPizza: TextView = itemView.findViewById(R.id.txtNome2)
        val txtCrepe: TextView = itemView.findViewById(R.id.txtNome4)
        val txtHamburguer: TextView = itemView.findViewById(R.id.txtNome5)
        val txtStatus: TextView = itemView.findViewById(R.id.txtId5)
        val btnMudarStatus: Button = itemView.findViewById(R.id.button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itens, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return produtos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]

        holder.txtNome.text = produto.nome
        holder.txtPizza.text = produto.pizza.toString()
        holder.txtCrepe.text = produto.crepe.toString()
        holder.txtHamburguer.text = produto.hamburguer.toString()
        holder.txtStatus.text = produto.status.toString()

        holder.btnMudarStatus.setOnClickListener {
            val novoStatus = "Pronto"

            dao.atualizarStatusPedido(produto.id.toString(), novoStatus) { sucesso ->
                if (sucesso) {
                    holder.txtStatus.text = novoStatus
                } else {
                }
            }
        }
    }
}
