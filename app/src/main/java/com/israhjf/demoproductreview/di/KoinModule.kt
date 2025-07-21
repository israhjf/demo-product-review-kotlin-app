package com.israhjf.demoproductreview.di

import com.israhjf.demoproductreview.dummyJsonApi.DummyJsonApiClientImpl
import com.israhjf.demoproductreview.dummyJsonApi.DummyJsonApiClient
import com.israhjf.demoproductreview.home.dao.HomeDao
import com.israhjf.demoproductreview.home.dao.HomeDaoImpl
import com.israhjf.demoproductreview.home.ui.HomeViewModel
import com.israhjf.demoproductreview.productDetails.dao.ProductDetailsDao
import com.israhjf.demoproductreview.productDetails.ui.ProductDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // API Service
    single<DummyJsonApiClient> { DummyJsonApiClientImpl() }

    // HomeViewModel
    single<HomeDao> { HomeDaoImpl(get()) }
    viewModel { HomeViewModel(get()) }

    // ProductDetailsViewModel
    single { ProductDetailsDao(get()) }
    viewModel { ProductDetailsViewModel(get()) }
}