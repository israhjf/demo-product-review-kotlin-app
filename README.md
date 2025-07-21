# Demo Product Review App (Android)

A modern Android app built with Jetpack Compose that demonstrates product listing and category browsing using the DummyJSON API.

## Features
- **API Integration**: Fetches data from [DummyJSON API](https://dummyjson.com/docs/products)

## Architecture
The app follows a MVVM architecture pattern with the following layers:

### Model (Data Layer)
Handles API, repositories, and data models.
- **DummyJsonApiClient**: Handles API calls to DummyJSON
- **HomeDao**: Data access object for home screen data
- **Models**: Data classes for Product and ProductsResponse
### ViewModel
Manages UI state and business logic.
- **HomeViewModel**: Manages UI state and business logic


### View (UI Layer)
Jetpack Compose UI components and screens.
- **HomeView**: Main UI component built with Jetpack Compose

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