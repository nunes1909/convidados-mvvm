package com.example.convidados.view.listener

import com.example.convidados.service.model.ConvidadoModel

interface ConvidadoListener {
    fun onClick(convidadoModel: ConvidadoModel)
    fun onDelete(convidadoModel: ConvidadoModel)
}