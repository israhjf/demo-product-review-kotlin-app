# Demo Product Review App

A modern Android app built with Jetpack Compose that demonstrates product listing and category browsing using the DummyJSON API.

## Features
- **API Integration**: Fetches data from [DummyJSON API](https://dummyjson.com/docs/products)

## Architecture
The app follows a MVVM architecture pattern with the following layers:

### Data Layer
- **DummyJsonApiClient**: Handles API calls to DummyJSON
- **HomeDao**: Data access object for home screen data
- **Models**: Data classes for Product, ProductsResponse, and CategoriesResponse

### Domain Layer
- **HomeViewModel**: Manages UI state and business logic
- **HomeUiState**: Represents the UI state

### Presentation Layer
- **HomeView**: Main UI component built with Jetpack Compose
- **Components**: Reusable UI components for products and categories

## Dependencies
- **Koin**: Dependency injection
- **Ktor**: HTTP client for API calls
- **Coil**: Image loading library

## API Integration
The app integrates with the DummyJSON API to fetch:
- Products: `GET /products`
- Categories: `GET /products/categories`
- Products by Category: `GET /products/category/{category}`

## Setup
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run the app