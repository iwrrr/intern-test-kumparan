package com.intern.test.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intern.test.data.entities.post.Post
import com.intern.test.data.entities.user.User
import com.intern.test.data.repositories.KumparanRepository
import com.intern.test.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: KumparanRepository
) : ViewModel() {

    private var _posts = MutableLiveData<Resource<List<Post>>>()
    val posts: LiveData<Resource<List<Post>>> = _posts

    fun getPosts() = viewModelScope.launch {
        _posts.value = Resource.Loading()

        val result = repository.getPosts()

        if (result.data == null) {
            _posts.value = Resource.Error("Failed to retrieve data")
        } else {
            _posts.value = Resource.Success(result.data)
        }
    }
}