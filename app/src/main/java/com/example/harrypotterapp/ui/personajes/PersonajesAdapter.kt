package com.example.harrypotterapp.ui.personajes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.harrypotterapp.R
import com.example.harrypotterapp.databinding.ItemPersonajeBinding
import com.example.harrypotterapp.utils.Character
import com.example.harrypotterapp.utils.House
import androidx.navigation.NavDirections
import androidx.navigation.findNavController


import android.widget.Filter
import android.widget.Filterable

class PersonajeAdapter(private var listaCasas: List<House>) : ListAdapter<Character, PersonajeAdapter.PersonajeViewHolder>(PersonajeDiffCallback()), Filterable {
    private var listaPersonajes: List<Character> = emptyList()  // Lista completa de personajes
    private var listaFiltrada: List<Character> = emptyList()  // Lista filtrada que se muestra

    init {
        // Inicializa la lista filtrada con todos los personajes al principio
        submitList(listaFiltrada)
    }

    fun setPersonajes(personajes: List<Character>) {
        this.listaPersonajes = personajes
        this.listaFiltrada = personajes
        submitList(listaFiltrada)
    }

    fun setCasas(casas: List<House>) {
        this.listaCasas = casas
        notifyDataSetChanged()  // Notificar para refrescar la lista si es necesario
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeViewHolder {
        val binding = ItemPersonajeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonajeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonajeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, listaCasas)
    }

    // Implementa Filterable
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString().trim()
                val filteredList = if (charSearch.isEmpty()) {
                    listaPersonajes
                } else {
                    listaPersonajes.filter {
                        it.fullName.lowercase().contains(charSearch.lowercase())
                    }
                }

                Log.d("PersonajeAdapter", "Filtrados: ${filteredList.size} elementos encontrados")

                return FilterResults().apply { values = filteredList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                results?.values?.let {
                    val listaFiltrada = it as List<Character>
                    submitList(listaFiltrada) // Actualiza la lista que el adaptador muestra
                    Log.d("PersonajeAdapter", "Lista actualizada con ${listaFiltrada.size} elementos")
                }
            }
        }
    }

    inner class PersonajeViewHolder(private val binding: ItemPersonajeBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val personaje = getItem(position)
                    val action = PersonajesFragmentDirections.actionNavigationPersonajesToPersonajeDetalleFragment(personaje)
                    binding.root.findNavController().navigate(action)

                }
            }
        }

        fun bind(item: Character, casas: List<House>) {
            binding.apply {
                textViewNombre.text = item.fullName
                textViewApodo.text = item.nickname

                val casa = casas.find { it.house == item.hogwartsHouse }
                textViewEmojiCasa.text = casa?.emoji ?: "" // Mostrar el emoji de la casa, o cadena vacía si no se encuentra

                Glide.with(itemView)
                    .load(item.image)
                    .placeholder(R.drawable.img) // Cambiado de img a placeholder
                    .error(R.drawable.error)
                    .into(imageViewPersonaje)
            }
        }
    }

    private class PersonajeDiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.fullName == newItem.fullName
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }
}
