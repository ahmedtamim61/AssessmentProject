package com.example.androidassessment.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Model ( @SerializedName("title") var title : String,
                  @SerializedName("rows") var rows : @RawValue ArrayList<Data>) : Parcelable

@Parcelize
data class Data( @SerializedName("title") var title : String,
                 @SerializedName("description") var description : String,
                 @SerializedName("imageHref") var imageHref : String) : Parcelable