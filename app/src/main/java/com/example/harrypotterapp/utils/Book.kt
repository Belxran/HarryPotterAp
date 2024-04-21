package com.example.harrypotterapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Book(
    val number: Int,
    val title: String,
    val originalTitle: String,
    val releaseDate: Date,
    val description: String,
    val pages: Int,
    val cover: String
)
