package com.israhjf.demoproductreview.productDetails.dao

import com.israhjf.demoproductreview.dummyJsonApi.DummyJsonApiClient
import com.israhjf.demoproductreview.dummyJsonApi.models.Product

class ProductDetailsDao(
    private val apiClient: DummyJsonApiClient
) {
    suspend fun getProductById(productId: Int): Product? {
        return apiClient.getProductById(productId)
    }
}