package com.example.harrypotterapp.utils

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class Character(
    val fullName: String,
    val nickname: String,
    val hogwartsHouse: String,
    val interpretedBy: String,
    val children: ArrayList<String>?,
    val image: String,
    val birthdate: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createStringArrayList(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fullName)
        parcel.writeString(nickname)
        parcel.writeString(hogwartsHouse)
        parcel.writeString(interpretedBy)
        parcel.writeStringList(children)
        parcel.writeString(image)
        parcel.writeString(birthdate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Character> {
        override fun createFromParcel(parcel: Parcel): Character {
            return Character(parcel)
        }

        override fun newArray(size: Int): Array<Character?> {
            return arrayOfNulls(size)
        }
    }
}
