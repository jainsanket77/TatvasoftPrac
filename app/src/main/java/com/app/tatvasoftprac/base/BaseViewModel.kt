package com.app.tatvasoftprac.base

import androidx.lifecycle.ViewModel
import com.app.tatvasoftprac.network.ApiService
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var provideApiService: ApiService

}