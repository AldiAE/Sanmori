package com.aldiavliar.sanmoriapps.data.Entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class HurufEntity (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int = 0,
        @ColumnInfo(name = "huruf")
        var huruf: String? = null,
        @ColumnInfo(name = "sandi")
        var sandi: String? = null,
        @ColumnInfo(name = "sound")
        var sound: String? = null,
) : Parcelable