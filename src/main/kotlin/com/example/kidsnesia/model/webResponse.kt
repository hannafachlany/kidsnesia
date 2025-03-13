package com.example.kidsnesia.model

data class WebResponse<T>(
    val message: T? = null,
    val status: String? = null
)
