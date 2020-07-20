package com.cahstudio.siswa.datasource.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Siswa(
    @SerializedName("id") val id: String?,
    @SerializedName("nama") val name: String?,
    @SerializedName("jenis_kelamin") val gender: String?,
    @SerializedName("jurusan_id") val jurusan: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(gender)
        parcel.writeString(jurusan)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Siswa> {
        override fun createFromParcel(parcel: Parcel): Siswa {
            return Siswa(parcel)
        }

        override fun newArray(size: Int): Array<Siswa?> {
            return arrayOfNulls(size)
        }
    }
}