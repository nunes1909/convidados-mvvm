package com.example.convidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.databinding.ConvidadoItemBinding
import com.example.convidados.service.model.ConvidadoModel
import com.example.convidados.view.listener.ConvidadoListener

class ConvidadoAdapter : RecyclerView.Adapter<ConvidadoAdapter.ConvidadoViewHolder>() {

    private var convidados: List<ConvidadoModel> = arrayListOf()
    private lateinit var mListener: ConvidadoListener

    class ConvidadoViewHolder(
        binding: ConvidadoItemBinding,
        private val listener: ConvidadoListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private val binding = binding


        fun vincula(convidado: ConvidadoModel) {
            val textNome = binding.textViewItem
            textNome.text = convidado.nome

            textNome.setOnClickListener {
                listener.onClick(convidado)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConvidadoViewHolder {
        val binding =
            ConvidadoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConvidadoViewHolder(binding, mListener)
    }

    override fun onBindViewHolder(holder: ConvidadoViewHolder, position: Int) {
        val convidado = convidados.get(position)
        holder.vincula(convidado)
    }

    override fun getItemCount() = convidados.size

    fun atualizaLista(lista: List<ConvidadoModel>) {
        convidados = lista
        notifyDataSetChanged()
    }

    fun preencheListener(listener: ConvidadoListener){
        mListener = listener
    }
}