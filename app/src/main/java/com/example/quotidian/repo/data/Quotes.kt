package com.example.quotidian.repo.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
@Entity(tableName = "Daily_Quotes")
data class Quotes(
    @SerializedName("q") val quote: String?,
    @SerializedName("a") val author: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "ID")  val id: Int = 0,
) : Parcelable