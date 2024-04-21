package com.example.harrypotterapp.ui.libros

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.text.SimpleDateFormat
import java.util.*

class DateTypeAdapter : TypeAdapter<Date>() {
    private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)


    override fun write(out: JsonWriter, value: Date?) {
        if (value == null) {
            out.nullValue()
        } else {
            out.value(dateFormat.format(value))
        }
    }

    override fun read(input: JsonReader): Date? {
        val dateStr = input.nextString()
        return if (dateStr == null || dateStr.isEmpty()) {
            null
        } else {
            try {
                dateFormat.parse(dateStr)
            } catch (e: Exception) {
                null
            }
        }
    }
}
