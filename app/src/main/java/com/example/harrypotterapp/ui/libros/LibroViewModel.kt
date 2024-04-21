package com.example.harrypotterapp.ui.libros

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotterapp.utils.Book
import com.example.harrypotterapp.utils.PotterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.harrypotterapp.utils.RetrofitClient


class LibroViewModel(private val repository: PotterRepository) : ViewModel() {
    private val _libros = MutableLiveData<List<Book>?>()
    val libros: MutableLiveData<List<Book>?> get() = _libros

    init {
        loadBooks()
    }

    private fun loadBooks() = viewModelScope.launch {
        _libros.value = withContext(Dispatchers.IO) { repository.getBooks("en") }
    }

    fun sortBooks(ascending: Boolean) {
        if (ascending) {
            _libros.value = _libros.value?.sortedBy { it.releaseDate }
        } else {
            _libros.value = _libros.value?.sortedByDescending { it.releaseDate }
        }
    }
}
