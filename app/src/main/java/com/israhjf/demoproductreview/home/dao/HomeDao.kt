package com.israhjf.demoproductreview.home.dao

import android.util.Log
import com.israhjf.demoproductreview.dummyJsonApi.models.Product
import com.israhjf.demoproductreview.dummyJsonApi.models.ProductsResponse

interface HomeDao {
    suspend fun getFeaturedProducts(limit: Int = 5): List<Product>
    suspend fun getAllProducts(limit: Int = 10, skip: Int = 0): ProductsResponse
}

class HomeDaoImpl(
    private val apiClient: com.israhjf.demoproductreview.dummyJsonApi.DummyJsonApiClient
): HomeDao {

    private val TAG = "HomeDaoImpl"

    override suspend fun getFeaturedProducts(limit: Int): List<Product> {
        return try {
            Log.d(TAG, "Fetching featured products with limit: $limit")
            val response = apiClient.getProducts(limit = limit)
            Log.d(TAG, "Successfully fetched ${response.products.size} featured products")
            if (response.products.isEmpty()) {
                Log.w(TAG, "No featured products returned from API")
            }
            response.products
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching featured products: ${e.message}", e)
            emptyList()
        }
    }

    override suspend fun getAllProducts(limit: Int, skip: Int): ProductsResponse {
        return try {
            Log.d(TAG, "Fetching all products with limit: $limit, skip: $skip")
            val response = apiClient.getProducts(limit = limit, skip = skip)
            Log.d(TAG, "Successfully fetched ${response.products.size} products")
            if (response.products.isEmpty()) {
                Log.w(TAG, "No products returned from API")
            }
            response
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching all products: ${e.message}", e)
            ProductsResponse(emptyList(), 0, skip, limit)
        }
    }
}