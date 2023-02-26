package me.hirotask.android.cafeapp.repository

import me.hirotask.android.cafeapp.domain.Cafe

interface CafeRepository {
    fun getCafeList(
        limit: Long,
        onSuccess: (List<Cafe>) -> Unit,
        onFailure: () -> Unit
    )

    fun addCafe(
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    )
}