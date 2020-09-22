package com.tolgahantutar.bexworkfloww.data.network

import com.google.gson.annotations.SerializedName
import com.tolgahantutar.bexworkfloww.data.models.AuthorizeSessionModel

data class AuthorizeSessionResponse (
    @SerializedName("Value")
    val authorizeSessionModel: AuthorizeSessionModel ,
    @SerializedName("Result")
    val Result : Boolean,
    @SerializedName("Description")
    val Description: String,
    @SerializedName("Code")
    val Code: String
)