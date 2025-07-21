package com.israhjf.demoproductreview.dummyJsonApi.models

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String? = null,
    val category: String,
    val thumbnail: String,
    val images: List<String>
)

@Serializable
data class ProductsResponse(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
)