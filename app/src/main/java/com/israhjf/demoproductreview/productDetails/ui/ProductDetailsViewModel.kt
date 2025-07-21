package com.israhjf.demoproductreview.productDetails.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.israhjf.demoproductreview.dummyJsonApi.models.Product
import com.israhjf.demoproductreview.productDetails.dao.ProductDetailsDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val productDetailsDao: ProductDetailsDao
): ViewModel() {
    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product.asStateFlow()

    fun loadProduct(productId: Int) {
        viewModelScope.launch {
            _product.value = productDetailsDao.getProductById(productId)
        }
    }
}