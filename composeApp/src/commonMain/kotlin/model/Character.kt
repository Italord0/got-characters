package model

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val fullName: String,
    val title: String,
    val family: String,
    val image: String,
    val imageUrl: String
)