package com.israhjf.demoproductreview.dummyJsonApi

import android.util.Log
import com.israhjf.demoproductreview.dummyJsonApi.models.ProductsResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

interface DummyJsonApiClient {
    suspend fun getProducts(limit: Int = 10, skip: Int = 0): ProductsResponse
}

class DummyJsonApiClientImpl : DummyJsonApiClient {
    private val TAG = "DummyJsonApiClient"
    private val baseUrl = "https://dummyjson.com"

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                prettyPrint = true
                ignoreUnknownKeys = true
                coerceInputValues = true
                encodeDefaults = true
            })
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d(TAG, message)
                }
            }
            level = LogLevel.BODY
        }
    }

    override suspend fun getProducts(
        limit: Int, skip: Int
    ): ProductsResponse = withContext(Dispatchers.IO) {
        try {
            Log.d(TAG, "Fetching products with limit: $limit, skip: $skip")
            val response = client.get("$baseUrl/products") {
                parameter("limit", limit)
                parameter("skip", skip)
            }
            val result = response.body<ProductsResponse>()
            Log.d(TAG, "Successfully fetched ${result.products.size} products")
            result
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching products: ${e.message}")
            throw e
        }
    }
}