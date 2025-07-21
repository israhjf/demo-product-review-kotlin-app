package com.israhjf.demoproductreview.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.israhjf.demoproductreview.R
import com.israhjf.demoproductreview.dummyJsonApi.models.Product
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeView(
    model: HomeViewModel = koinViewModel()
) {
    val featuredProducts by model.featuredProducts.collectAsState()
    val filteredProducts by model.filteredProducts.collectAsState()
    val searchQuery by model.searchQuery.collectAsState()
    val isLoading by model.isLoading.collectAsState()
    val error by model.error.collectAsState()
    
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HeaderSection()
            
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (error != null) {
                ErrorSection(
                    error = error!!,
                    onRetry = { model.refreshData() }
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        FeaturedProductsSection(
                            featuredProducts = featuredProducts,
                            onProductClick = { /* TODO: Navigate to product detail */ }
                        )
                    }

                    item {
                        BrowseProductsSection(
                            products = filteredProducts,
                            onProductClick = { /* TODO: Navigate to product detail */ },
                            searchQuery = searchQuery,
                            onSearchQueryChange = { model.onSearchQueryChange(it) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchBarSection(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        placeholder = {
            Text(
                text = "Search products...",
                color = Color.Black,
                fontSize = 14.sp
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
        },
        singleLine = true,
        textStyle = androidx.compose.ui.text.TextStyle(
            fontSize = 14.sp
        )
    )
}

@Composable
private fun ErrorSection(
    error: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Error: $error",
            color = Color.Red,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}

@Composable
private fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(225.dp)
            .background(Color.Red)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_shopping_cart),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        IconButton(
            onClick = { /* TODO: Handle menu click */ },
            modifier = Modifier
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = null,
                tint = Color.Black
            )
        }
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 32.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF424242)
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.welcome_to),
                    color = Color(0xFFBDBDBD),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.demo_product_review),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun FeaturedProductsSection(
    featuredProducts: List<Product>,
    onProductClick: (Product) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.featured_products),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (featuredProducts.isNotEmpty()) {
            val firstProduct = featuredProducts.first()
            FeaturedProductCard(
                product = firstProduct,
                onClick = { onProductClick(firstProduct) }
            )

            Spacer(modifier = Modifier.height(16.dp))
        } else {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Box {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFE0E0E0))
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "No featured products available",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FeaturedProductCard(
    product: Product,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp)
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = product.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(
                        Color(0xFF1976D2).copy(alpha = 0.9f),
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                    .padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = product.title,
                        color = Color.White,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "$${product.price}",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun SmallProductCard(
    product: Product,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(160.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = product.title,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "$${product.price}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun BrowseProductsSection(
    products: List<Product>,
    onProductClick: (Product) -> Unit,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.browse_products),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        SearchBarSection(
            searchQuery = searchQuery,
            onSearchQueryChange = onSearchQueryChange
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (products.isNotEmpty()) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val productRows = products.chunked(3)
                
                productRows.forEach { rowProducts ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        rowProducts.forEach { product ->
                            SmallProductCard(
                                product = product,
                                onClick = { onProductClick(product) }
                            )
                        }
                        
                        repeat(3 - rowProducts.size) {
                            Spacer(modifier = Modifier.width(120.dp))
                        }
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No products available",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
        }
    }
}