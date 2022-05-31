package com.example.convidados.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.databinding.ActivityFormularioConvidadoBinding
import com.example.convidados.service.model.ConvidadoModel
import com.example.convidados.viewmodel.FormularioViewModel

class FormularioActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioConvidadoBinding.inflate(layoutInflater)
    }

    private var convidadoId: Int = 0
    private lateinit var viewModel: FormularioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(FormularioViewModel::class.java)

        listener()
        loadData()
        observer()

        binding.radioPresente.isChecked = true
    }

    private fun loadData() {
        intent.getParcelableExtra<ConvidadoModel>("convidado")?.let {
            convidadoId = it.id
            viewModel.load(it.id)
        }
    }

    private fun listener() {
        binding.buttonSalvar.setOnClickListener {
            val nome = binding.textNome.text.toString()
            val presenca = binding.radioPresente.isChecked

            viewModel.salvar(convidadoId, nome, presenca)
        }
    }

    private fun observer() {
        viewModel.salvarConvidado.observe(this, Observer {
            if (it) {
                Toast.makeText(this@FormularioActivity, "Sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@FormularioActivity, "Falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })

        viewModel.carregaConvidado.observe(this, Observer {
            val textNome = binding.textNome
            val presente = binding.radioPresente
            val ausente = binding.radioAusente

            textNome.setText(it.nome)
            if (it.presenca) presente.isChecked = true
            else ausente.isChecked = true
        })
    }
}