package com.example.mango_app.utils

import android.content.Context
import java.lang.ref.WeakReference

object ErrorMessagesProvider {
    private var contextRef: WeakReference<Context>? = null

    fun init(context: Context) {
        contextRef = WeakReference(context.applicationContext)
    }

    fun getErrorMessage(resId: Int): String {
        val context = contextRef?.get()
            ?: throw IllegalStateException("ErrorMessages not initialized. Call ErrorMessages.init(context) in your Application class.")
        return context.getString(resId)
    }
}
