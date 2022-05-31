package com.example.convidados.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.databinding.ConvidadoItemBinding
import com.example.convidados.service.model.ConvidadoModel

class ConvidadoViewHolder(binding: ConvidadoItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private val binding = binding

    fun vincula(convidado: ConvidadoModel) {
        binding.textViewItem.text = convidado.nome
    }

}
