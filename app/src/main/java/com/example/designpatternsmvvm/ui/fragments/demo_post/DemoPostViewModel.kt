package com.example.designpatternsmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.imaginativeworld.simplemvvm.models.DemoPost
import org.imaginativeworld.simplemvvm.network.ApiException
import org.imaginativeworld.simplemvvm.repositories.AppRepository
import javax.inject.Inject

@HiltViewModel
class DemoPostViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private val _eventShowMessage: MutableLiveData<String?> by lazy {
        MutableLiveData<String?>()
    }

    val eventShowMessage: LiveData<String?>
        get() = _eventShowMessage

    // ----------------------------------------------------------------

    private val _eventShowLoading: MutableLiveData<Boolean?> by lazy {
        MutableLiveData<Boolean?>()
    }

    val eventShowLoading: LiveData<Boolean?>
        get() = _eventShowLoading

    // ----------------------------------------------------------------

    private val _postItems: MutableLiveData<List<DemoPost>> by lazy {
        MutableLiveData<List<DemoPost>>()
    }

    val postItems: LiveData<List<DemoPost>?>
        get() = _postItems

    // ----------------------------------------------------------------

    fun getPosts() = viewModelScope.launch {

        _eventShowLoading.value = true

        try {
            val postResponse = repository.getPosts()

            _postItems.value = postResponse.data
        } catch (e: ApiException) {
            _eventShowMessage.value = e.message
        }

        _eventShowLoading.value = false

    }


}