package com.example.harrypotterapp.ui.libros

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harrypotterapp.utils.PotterRepository

class LibroViewModelFactory(private val repository: PotterRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LibroViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LibroViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
