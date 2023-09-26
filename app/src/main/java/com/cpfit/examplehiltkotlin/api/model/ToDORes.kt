package com.cpfit.examplehiltkotlin.api.model

data class ToDORes(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)