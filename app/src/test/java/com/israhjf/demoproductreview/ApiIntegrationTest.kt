package com.israhjf.demoproductreview

import com.israhjf.demoproductreview.dummyJsonApi.DummyJsonApiClientImpl
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertTrue

class ApiIntegrationTest {
    
    @Test
    fun testGetProducts() = runBlocking {
        val apiClient = DummyJsonApiClientImpl()
        val productsResponse = apiClient.getProducts(limit = 5)
        
        assertTrue(productsResponse.products.isNotEmpty(), "Products list should not be empty")
        assertTrue(productsResponse.total > 0, "Total products should be greater than 0")
        println("Retrieved ${productsResponse.products.size} products")
        println("First product: ${productsResponse.products.first().title}")
    }
} 