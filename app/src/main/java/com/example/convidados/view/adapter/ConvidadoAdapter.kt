package com.example.convidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.databinding.ConvidadoItemBinding
import com.example.convidados.service.model.ConvidadoModel
import com.example.convidados.view.viewholder.ConvidadoViewHolder

class ConvidadoAdapter() : RecyclerView.Adapter<ConvidadoViewHolder>() {

    private var convidados: List<ConvidadoModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConvidadoViewHolder {
        val binding =
            ConvidadoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConvidadoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConvidadoViewHolder, position: Int) {
        val convidado = convidados.get(position)
        holder.vincula(convidado)
    }

    override fun getItemCount() = convidados.size

    fun atualizaLista(lista: List<ConvidadoModel>){
        convidados = lista
        notifyDataSetChanged()
    }
}