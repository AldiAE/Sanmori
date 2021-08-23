package com.aldiavliar.sanmoriapps.data.Entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class PenggalangEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int = 0,
        @ColumnInfo(name = "kategori")
        var kategori: String? = null,
        @ColumnInfo(name = "point")
        var point: String? = null,
        @ColumnInfo(name = "desk_point")
        var desk_point: String? = null,
        @ColumnInfo(name = "cara_pengujian")
        var cara_pengujian: String? = null,
        @ColumnInfo(name = "status")
        var status: String? = null

) : Parcelable