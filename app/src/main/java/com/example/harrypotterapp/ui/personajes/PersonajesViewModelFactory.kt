package com.example.harrypotterapp.ui.personajes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harrypotterapp.utils.PotterRepository

class PersonajesViewModelFactory(private val repository: PotterRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonajesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PersonajesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
