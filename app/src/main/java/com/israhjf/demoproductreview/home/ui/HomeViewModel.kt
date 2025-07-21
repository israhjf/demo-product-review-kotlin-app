package com.israhjf.demoproductreview.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.israhjf.demoproductreview.home.dao.HomeDao
import com.israhjf.demoproductreview.dummyJsonApi.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeDao: HomeDao
): ViewModel() {

    private val TAG = "HomeViewModel"
    
    private val _featuredProducts = MutableStateFlow<List<Product>>(emptyList())
    val featuredProducts: StateFlow<List<Product>> = _featuredProducts.asStateFlow()
    
    private val _allProducts = MutableStateFlow<List<Product>>(emptyList())
    val allProducts: StateFlow<List<Product>> = _allProducts.asStateFlow()
    
    private val _filteredProducts = MutableStateFlow<List<Product>>(emptyList())
    val filteredProducts: StateFlow<List<Product>> = _filteredProducts.asStateFlow()
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadHomeData()
        testApiConnection()
    }

    private fun loadHomeData() {
        viewModelScope.launch {
            try {
                Log.d(TAG, "Loading home data...")
                _isLoading.value = true
                _error.value = null
                
                val featuredProducts = homeDao.getFeaturedProducts(5)
                val allProductsResponse = homeDao.getAllProducts(limit = 50, skip = 0)
                
                Log.d(TAG, "Loaded ${featuredProducts.size} featured products and ${allProductsResponse.products.size} total products")
                
                if (featuredProducts.isEmpty() && allProductsResponse.products.isEmpty()) {
                    Log.w(TAG, "No products loaded - this might indicate an API issue")
                }
                
                _featuredProducts.value = featuredProducts
                _allProducts.value = allProductsResponse.products
                _filteredProducts.value = allProductsResponse.products
                _isLoading.value = false
            } catch (e: Exception) {
                Log.e(TAG, "Error loading home data: ${e.message}", e)
                _isLoading.value = false
                _error.value = e.message ?: "Unknown error occurred"
            }
        }
    }

    private fun testApiConnection() {
        viewModelScope.launch {
            try {
                Log.d(TAG, "Testing API connection...")
                val testResponse = homeDao.getAllProducts(limit = 1, skip = 0)
                Log.d(TAG, "API test successful: ${testResponse.products.size} products returned")
            } catch (e: Exception) {
                Log.e(TAG, "API test failed: ${e.message}", e)
            }
        }
    }

    fun refreshData() {
        Log.d(TAG, "Refreshing data...")
        loadHomeData()
    }

    fun onSearchQueryChange(query: String) {
        Log.d(TAG, "Search query changed to: $query")
        _searchQuery.value = query
        
        val filteredProducts = if (query.isBlank()) {
            _allProducts.value
        } else {
            _allProducts.value.filter { product ->
                product.title.contains(query, ignoreCase = true) ||
                product.description.contains(query, ignoreCase = true) ||
                (product.brand?.contains(query, ignoreCase = true) == true) ||
                product.category.contains(query, ignoreCase = true)
            }
        }
        
        _filteredProducts.value = filteredProducts
    }

    fun getFeaturedProducts(): List<Product> {
        return _featuredProducts.value
    }

    fun getFilteredProducts(): List<Product> {
        return _filteredProducts.value
    }

    fun getSearchQuery(): String {
        return _searchQuery.value
    }

    fun isLoading(): Boolean {
        return _isLoading.value
    }

    fun getError(): String? {
        return _error.value
    }
}