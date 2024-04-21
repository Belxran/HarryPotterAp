package com.example.harrypotterapp.ui.personajes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotterapp.utils.Character
import com.example.harrypotterapp.utils.House
import com.example.harrypotterapp.utils.PotterRepository
import kotlinx.coroutines.launch

class PersonajesViewModel(private val repository: PotterRepository) : ViewModel() {

    private val _listaCasas = MutableLiveData<List<House>>()
    val listaCasas: LiveData<List<House>> get() = _listaCasas

    private val _personajes = MutableLiveData<List<Character>>()
    val personajes: LiveData<List<Character>> = _personajes

    init {
        cargarPersonajes("en")
        cargarCasas("en")
    }

    fun cargarPersonajes(lang: String) {
        viewModelScope.launch {
            try {
                val listaPersonajes = repository.getCharacters(lang)
                _personajes.value = listaPersonajes
                Log.d("PersonajesViewModel", "Personajes cargados: ${listaPersonajes.size}")
            } catch (ex: Exception) {
                Log.e("PersonajesViewModel", "Error al cargar personajes", ex)
            }
        }
    }


    fun cargarCasas(lang: String) {
        viewModelScope.launch {
            try {
                val listaCasas = repository.getHouses(lang)
                _listaCasas.value = listaCasas
            } catch (ex: Exception) {
                // Manejar el error, si es necesario
            }
        }
    }
}
