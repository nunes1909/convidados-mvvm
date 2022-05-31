package com.example.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidados.databinding.FragmentTodosBinding
import com.example.convidados.service.constants.TODOS
import com.example.convidados.service.model.ConvidadoModel
import com.example.convidados.view.adapter.ConvidadoAdapter
import com.example.convidados.view.listener.ConvidadoListener
import com.example.convidados.viewmodel.ConvidadosViewModel

class TodosFragment : Fragment() {


    private lateinit var convidadosViewModel: ConvidadosViewModel
    private val adapter by lazy {
        ConvidadoAdapter()
    }
    private lateinit var listener: ConvidadoListener

    private var _binding: FragmentTodosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        convidadosViewModel =
            ViewModelProvider(this).get(ConvidadosViewModel::class.java)

        _binding = FragmentTodosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //define recycler
        val recycler = binding.recyclerTodosConvidados
        //define layout
        recycler.layoutManager = LinearLayoutManager(context)
        //define adaper
        recycler.adapter = adapter

        listener = object : ConvidadoListener{
            override fun onClick(convidadoModel: ConvidadoModel) {
                Intent(context, FormularioActivity::class.java).apply {
                    putExtra("convidado", convidadoModel)
                    startActivity(this)
                }
            }

            override fun onDelete(convidadoModel: ConvidadoModel) {
                convidadosViewModel.delete(convidadoModel)
                convidadosViewModel.carregaLista(TODOS)
            }
        }

        adapter.preencheListener(listener)

        convidadosViewModel.todosConvidados.observe(viewLifecycleOwner, Observer { listaConvidados ->
            listaConvidados?.let { listaConvidados ->
                adapter.atualizaLista(listaConvidados)
            }
        })

        return root
    }

    override fun onResume() {
        super.onResume()
        convidadosViewModel.carregaLista(TODOS)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}