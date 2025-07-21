package com.israhjf.demoproductreview.productDetails.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductDetailsView(
    productId: Int
) {
    val viewModel: ProductDetailsViewModel = koinViewModel()
    val product by viewModel.product.collectAsState()

    LaunchedEffect(productId) {
        viewModel.loadProduct(productId)
    }

    if (product == null) {
        // Show loading or error
        Text("Loading product details...")
    } else {
        // Show product details
        Text("Product: ${product!!.title}")
        // ... more UI as needed
    }
}