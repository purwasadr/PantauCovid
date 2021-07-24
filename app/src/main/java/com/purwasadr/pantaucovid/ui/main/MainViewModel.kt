package com.purwasadr.pantaucovid.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.purwasadr.pantaucovid.data.IAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: IAppRepository
) : ViewModel() {
    val getCovidData = repository.getCovidData().asLiveData()
}