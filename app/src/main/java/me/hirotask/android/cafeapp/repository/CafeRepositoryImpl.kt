package me.hirotask.android.cafeapp.repository

import com.google.firebase.firestore.FirebaseFirestore
import me.hirotask.android.cafeapp.domain.Cafe
import me.hirotask.android.cafeapp.domain.toCafe

class CafeRepositoryImpl : CafeRepository {

    companion object {
        const val COLLECTION = "CafeList"
    }

    private val database: FirebaseFirestore get() = FirebaseFirestore.getInstance()

    override fun getCafeList(
        limit: Long,
        onSuccess: (List<Cafe>) -> Unit,
        onFailure: () -> Unit
    ) {
        val collection = database.collection(COLLECTION)
        collection.limit(limit).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSuccess(task.result.map { it.data }.map { it.toCafe() })
            } else {
                onFailure()
            }
        }
    }

    override fun addCafe(
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        TODO("Not yet implemented")
    }
}