package com.kotasprojects.android.spacexlaunch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotasprojects.android.spacexlaunch.network.SpaceXApi
import com.kotasprojects.android.spacexlaunch.network.SpaceXApiFilter
import com.kotasprojects.android.spacexlaunch.network.SpaceXLunchProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class SpaceXApiStatus { LOADING, ERROR, DONE }

class MainViewModel : ViewModel() {

    private val _status = MutableLiveData<SpaceXApiStatus>()

    val status: LiveData<SpaceXApiStatus>
        get() = _status

    private val _property = MutableLiveData<List<SpaceXLunchProperty>>()

    val property: LiveData<List<SpaceXLunchProperty>>
        get() = _property

    private val _navigateToSelectedProperty = MutableLiveData<SpaceXLunchProperty>()
    val navigateToSelectedProperty: LiveData<SpaceXLunchProperty>
        get() = _navigateToSelectedProperty

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getSpaceXLunchProperties(SpaceXApiFilter.SHOW_ALL)
    }

    private fun getSpaceXLunchProperties(filter: SpaceXApiFilter) {

        coroutineScope.launch {
            var getPropertiesDeferred = SpaceXApi.retrofitService.getProperties(filter.value)
            try {
                _status.value = SpaceXApiStatus.LOADING

                val listResult = getPropertiesDeferred.await()
                _status.value = SpaceXApiStatus.DONE
                _property.value = listResult

            } catch (e: Exception) {
                _status.value = SpaceXApiStatus.ERROR
                _property.value = ArrayList()
            }

        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayPropertyDetails(spaceXLunchProperty: SpaceXLunchProperty) {
        _navigateToSelectedProperty.value = spaceXLunchProperty

    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    fun updateFilter(filter: SpaceXApiFilter) {
        getSpaceXLunchProperties(filter)
    }

}