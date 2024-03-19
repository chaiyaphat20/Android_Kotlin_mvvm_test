package com.cpfit.examplehiltkotlin.model

data class ModelDetail(
    val name: String,
    val imageUrl: String,
    val desc: String,
    val price: String,
    val type: String,
    val id: String,
    val departmentId: String,
)

//{
//    "name": "Recycled Plastic Tuna",
//    "imageUrl": "https://loremflickr.com/640/480",
//    "desc": "Boston's most advanced compression wear technology increases muscle oxygenation, stabilizes active muscles",
//    "price": "398.00",
//    "type": "normal",
//    "id": "1",
//    "departmentId": "1"
//}