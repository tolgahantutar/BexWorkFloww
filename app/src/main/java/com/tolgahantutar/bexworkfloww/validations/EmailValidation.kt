package com.tolgahantutar.bexworkfloww.validations

import java.util.regex.Pattern

class EmailValidation() {
    val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    fun checkEmail(email: String) : Boolean{
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }
}