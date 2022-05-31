package com.example.convidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidados.databinding.FragmentTodosBinding
import com.example.convidados.service.model.ConvidadoModel
import com.example.convidados.view.adapter.ConvidadoAdapter
import com.example.convidados.viewmodel.TodosViewModel

class TodosFragment : Fragment() {

    private val adapter by lazy {
        ConvidadoAdapter()
    }

    private var convidados: List<ConvidadoModel>? = null

    private var _binding: FragmentTodosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val todosViewModel =
            ViewModelProvider(this).get(TodosViewModel::class.java)

        _binding = FragmentTodosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //define recycler
        val recycler = binding.recyclerTodosConvidados
        //define layout
        recycler.layoutManager = LinearLayoutManager(context)
        //define adaper
        recycler.adapter = adapter

        todosViewModel.todosConvidados.observe(viewLifecycleOwner, Observer { listaConvidados ->
            listaConvidados?.let { listaConvidados ->
                adapter.atualizaLista(listaConvidados)
                convidados = listaConvidados
            }
        })
        todosViewModel.carregaLista()


        return root
    }

    override fun onResume() {


        super.onResume()
        adapter.atualizaLista(convidados!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}