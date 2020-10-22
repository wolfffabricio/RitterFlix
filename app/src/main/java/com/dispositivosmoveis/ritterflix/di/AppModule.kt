package com.dispositivosmoveis.ritterflix.di

import com.dispositivosmoveis.ritterflix.ui.detail.MovieDetailViewModel
import com.dispositivosmoveis.ritterflix.ui.home.HomeViewModel
import com.dispositivosmoveis.ritterflix.ui.login.LoginViewModel
import com.dispositivosmoveis.ritterflix.ui.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        SplashViewModel()
    }

    viewModel {
        LoginViewModel(context = androidContext())
    }

    viewModel {
        HomeViewModel()
    }

    viewModel {
        MovieDetailViewModel()
    }
}