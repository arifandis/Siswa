package com.cahstudio.siswa.datasource.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Jurusan(
    @SerializedName("id") val id: String?,
    @SerializedName("jurusan") val jurusan: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(jurusan)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Jurusan> {
        override fun createFromParcel(parcel: Parcel): Jurusan {
            return Jurusan(parcel)
        }

        override fun newArray(size: Int): Array<Jurusan?> {
            return arrayOfNulls(size)
        }
    }
}