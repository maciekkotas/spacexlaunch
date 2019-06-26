package com.kotasprojects.android.spacexlaunch.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotasprojects.android.spacexlaunch.network.SpaceXLunchProperty


class DetailViewModel(spaceXLaunchProperty: SpaceXLunchProperty, app: Application) : AndroidViewModel(app) {


    private val _selectedProperty = MutableLiveData<SpaceXLunchProperty>()

    val selectedProperty: LiveData<SpaceXLunchProperty>
        get() = _selectedProperty

    init {
        _selectedProperty.value = spaceXLaunchProperty
    }
}
