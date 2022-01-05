package com.intern.test.ui.detail.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intern.test.data.entities.album.Album
import com.intern.test.data.repositories.KumparanRepository
import com.intern.test.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailUserViewModel @Inject constructor(
    private val repository: KumparanRepository
) : ViewModel() {

    private var _albums = MutableLiveData<Resource<List<Album>>>()
    val album: LiveData<Resource<List<Album>>> = _albums

    fun getAlbums(userId: Int) = viewModelScope.launch {
        _albums.value = Resource.Loading()

        val result = repository.getAlbums(userId)

        if (result.data == null) {
            _albums.value = Resource.Error("Failed to retrieve data")
        } else {
            _albums.value = Resource.Success(result.data)
        }
    }
}