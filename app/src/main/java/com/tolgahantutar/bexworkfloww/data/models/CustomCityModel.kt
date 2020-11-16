package com.tolgahantutar.bexworkfloww.data.models

class CustomCityModel(name: String, id: Int) {
    val name = name
    val id = id

    override fun toString(): String {
        return name
    }
}