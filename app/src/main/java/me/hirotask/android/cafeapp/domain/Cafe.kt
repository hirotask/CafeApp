package me.hirotask.android.cafeapp.domain

import android.location.Location
import java.util.*

data class Cafe(
    val id: String,
    val createdAt: Date,
    val name: String,
    val location: Location
) {
    companion object {
        fun create(name: String, location: Location) =
            Cafe(UUID.randomUUID().toString(), Date(), name, location)
    }
}

fun Map<String, Any>.toCafe(): Cafe {
    val id = this["id"] as String
    val createdAt = this["createdAt"] as Date
    val name = this["name"] as String
    val location = this["location"] as Location

    return Cafe(id, createdAt, name, location)
}
