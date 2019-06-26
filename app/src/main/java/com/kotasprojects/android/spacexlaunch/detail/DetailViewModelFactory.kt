package com.kotasprojects.android.spacexlaunch.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotasprojects.android.spacexlaunch.network.SpaceXLunchProperty

class DetailViewModelFactory(
    private val spaceXLunchProperty: SpaceXLunchProperty,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(spaceXLunchProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}