package com.example.mango_app.viewmodel

class CommonValidations {
    companion object {
        fun isValidFullName(fullName: String): Boolean {
            return "^[a-zA-Z]+ [a-zA-Z]+$".toRegex().matches(fullName)
        }

        fun isValidEmail(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isValidPhone(phone: String): Boolean {
            return "^[0-9]{10}$".toRegex().matches(phone)
        }

        fun isValidPassword(password: String): Boolean {
            return password.length >= 6
        }

        fun isValidRepeatPassword(password: String, repeatPassword: String): Boolean {
            return password == repeatPassword
        }
    }
}