package com.example.harrypotterapp.ui.personajes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.harrypotterapp.R
import com.example.harrypotterapp.databinding.FragmentPersonajeDetalleBinding

class PersonajeDetalleFragment : Fragment() {

    private var _binding: FragmentPersonajeDetalleBinding? = null
    private val binding get() = _binding!!

    private val args: PersonajeDetalleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonajeDetalleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val personaje = args.personaje

        personaje?.let {
            binding.apply {
                textViewNombre.text = it.fullName
                textViewApodo.text = it.nickname
                textViewCasa.text = it.hogwartsHouse
                textViewDescendencia.text = it.children.toString()
                textViewBirthdate.text = it.birthdate


                Glide.with(requireContext())
                    .load(it.image)
                    .placeholder(R.drawable.img)
                    .error(R.drawable.error)
                    .into(imageViewPersonaje)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
