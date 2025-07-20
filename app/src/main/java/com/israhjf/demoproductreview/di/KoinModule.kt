package com.israhjf.demoproductreview.di

import com.israhjf.demoproductreview.home.dao.HomeDao
import com.israhjf.demoproductreview.home.dao.HomeDaoImpl
import com.israhjf.demoproductreview.home.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // HomeViewModel
    single<HomeDao> {HomeDaoImpl()}
    viewModel{ HomeViewModel(get())}
}