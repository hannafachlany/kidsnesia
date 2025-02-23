package com.example.kidsnesia.model

data class webResponse<T>(
    val data: T? = null, // Nullable jika data bisa kosong
    val errors: String? = null // Nullable jika errors bisa kosong
)
