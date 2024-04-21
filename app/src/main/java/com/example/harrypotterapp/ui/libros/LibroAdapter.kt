package com.example.harrypotterapp.ui.libros


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.harrypotterapp.R
import com.example.harrypotterapp.databinding.ItemLibroBinding
import com.example.harrypotterapp.utils.Book
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.*

class LibroAdapter(
    private var libros: List<Book>,
    private val onBookClick: (Book) -> Unit
) : RecyclerView.Adapter<LibroAdapter.LibroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val binding = ItemLibroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LibroViewHolder(binding, parent, onBookClick)
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        holder.bind(libros[position])
    }

    override fun getItemCount(): Int = libros.size

    class LibroViewHolder(
        private val binding: ItemLibroBinding,
        private val parent: ViewGroup,
        private val onBookClick: (Book) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)

        fun bind(book: Book) {
            binding.textViewTitulo.text = book.title
            binding.textViewFechaPublicacion.text = dateFormat.format(book.releaseDate)
            Glide.with(binding.imageViewLibro.context)
                .load(book.cover)
                .placeholder(com.example.harrypotterapp.R.drawable.img_2)
                .into(binding.imageViewLibro)

            binding.root.setOnClickListener {
                showBookDetailsDialog(book)
                onBookClick(book)
            }
        }

        private fun showBookDetailsDialog(book: Book) {
            val dialogView = LayoutInflater.from(parent.context).inflate(R.layout.dialog_book_details, null)
            val title = dialogView.findViewById<TextView>(R.id.dialogTitle)
            val description = dialogView.findViewById<TextView>(R.id.dialogDescription)

            title.text = book.title.toUpperCase(Locale.getDefault())
            description.text = book.description

            MaterialAlertDialogBuilder(parent.context)
                .setView(dialogView)
                .setPositiveButton("Close", null)
                .show()
        }


    }

    fun updateData(newBooks: List<Book>) {
        libros = newBooks
        notifyDataSetChanged()
    }
}
