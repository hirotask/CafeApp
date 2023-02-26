package me.hirotask.android.cafeapp.repository

import me.hirotask.android.cafeapp.domain.Cafe

interface FirestoreRepository {
    fun getCafeList(): List<Cafe>

    fun addCafe()
}