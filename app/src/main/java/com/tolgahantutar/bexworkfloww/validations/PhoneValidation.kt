package com.tolgahantutar.bexworkfloww.validations

import java.util.regex.Pattern

class PhoneValidation(){
    val PHONE_NUMBER_PATTERN: Pattern= Pattern.compile(
        "^[+][0-9]{12,12}\$"
    )
    fun checkPhoneNumber(phoneNumber: String): Boolean{
        return PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches()
    }
}