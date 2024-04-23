package com.example.harrypotterapp.ui.personajes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harrypotterapp.databinding.FragmentPersonajesBinding
import com.example.harrypotterapp.utils.Character
import com.example.harrypotterapp.utils.PotterRepositoryImpl
import com.example.harrypotterapp.utils.RetrofitClient

class PersonajesFragment : Fragment() {

    private var _binding: FragmentPersonajesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PersonajesViewModel
    private lateinit var adapter: PersonajeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPersonajesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adapter = PersonajeAdapter(emptyList())
        binding.recyclerViewPersonajes.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewPersonajes.adapter = adapter

        val service = RetrofitClient.service
        val repository = PotterRepositoryImpl(service)
        val viewModelFactory = PersonajesViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PersonajesViewModel::class.java)

        viewModel.personajes.observe(viewLifecycleOwner, Observer<List<Character>> { personajes ->
            adapter.setPersonajes(personajes)
        })

        viewModel.listaCasas.observe(viewLifecycleOwner, Observer { casas ->
            adapter.setCasas(casas)
        })

        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })

        return root
    }

    override fun onResume() {
        super.onResume()
         viewModel.cargarPersonajes("en")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}