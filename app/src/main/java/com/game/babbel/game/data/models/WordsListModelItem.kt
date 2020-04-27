package com.game.babbel.game.data.models


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class WordsListModelItem(
    @Expose @SerializedName("text_eng")
    var textEng: String?,
    @Expose @SerializedName("text_spa")
    var textSpa: String?
) : Parcelable