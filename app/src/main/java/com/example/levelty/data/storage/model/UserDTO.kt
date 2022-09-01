package com.example.levelty.data.storage.model

data class UserDTO (
    val id: Int? = null,
    val countryCode: String? = null,
    val notificationTypes: List<String?>? = null,
    val isStaff: Boolean? = null,
    val dateOfBirth: String? = null,
    val phoneNumberConfirmed: Boolean? = null,
    val lastName: String? = null,
    val phoneNumber: String? = null,
    val language: String? = null,
    val firstName: String? = null
        )