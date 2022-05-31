package com.example.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.databinding.ActivityFormularioConvidadoBinding
import com.example.convidados.viewmodel.FormularioViewModel

class FormularioActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioConvidadoBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: FormularioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(FormularioViewModel::class.java)

        viewModel.salvarConvidado.observe(this, Observer {
            if (it){
                Toast.makeText(this@FormularioActivity, "Sucesso", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@FormularioActivity, "Falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })

        binding.buttonSalvar.setOnClickListener {
            val nome = binding.textNome.text.toString()
            val presenca = binding.radioPresente.isChecked

            viewModel.salvar(nome, presenca)
        }
    }
}