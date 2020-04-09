package com.magnilsonti.posts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.magnilsonti.posts.service.model.Post
import com.magnilsonti.posts.service.repository.PostRepository

class PostViewModel(application: Application) : AndroidViewModel(application) {

    private val mPostRepository = PostRepository.getInstance(application.applicationContext)

    private val mPostList = MutableLiveData<List<Post>>()
    val postList: LiveData<List<Post>> = mPostList

    fun load() {
        mPostList.value = mPostRepository.getAll()
    }

    fun delete(id: Int) {
        mPostRepository.delete(id)
    }

}