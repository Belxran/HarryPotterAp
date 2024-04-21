package com.example.harrypotterapp.ui.libros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harrypotterapp.R
import com.example.harrypotterapp.databinding.FragmentLibrosBinding
import com.example.harrypotterapp.utils.Book
import com.example.harrypotterapp.utils.PotterRepositoryImpl
import com.example.harrypotterapp.utils.RetrofitClient
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LibroFragment : Fragment() {

    private lateinit var binding: FragmentLibrosBinding
    private lateinit var viewModel: LibroViewModel
    private lateinit var adapter: LibroAdapter
    private var ascending = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLibrosBinding.inflate(inflater, container, false)
        val service = RetrofitClient.service

        val repository = PotterRepositoryImpl(service)


        val factory = LibroViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory).get(LibroViewModel::class.java)

        setupRecyclerView()

        viewModel.libros.observe(viewLifecycleOwner) { libros ->
            libros?.let { adapter.updateData(it) }
        }

        binding.buttonSort.setOnClickListener {
            viewModel.sortBooks(ascending)
            ascending = !ascending
            updateSortButtonIcon()
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = LibroAdapter(emptyList()) { book ->
            // Lógica para mostrar detalles del libro seleccionado
        }
        binding.recyclerViewLibros.adapter = adapter
        binding.recyclerViewLibros.layoutManager = LinearLayoutManager(context)
    }

    private fun updateSortButtonIcon() {
        val iconResId = if (ascending) R.drawable.img_5 else R.drawable.img_4
        binding.buttonSort.setImageResource(iconResId)
    }


}
