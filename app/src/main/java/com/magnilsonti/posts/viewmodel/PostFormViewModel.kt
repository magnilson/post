package com.magnilsonti.posts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.magnilsonti.posts.service.model.Post
import com.magnilsonti.posts.service.repository.PostRepository

class PostFormViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val mPostRepository: PostRepository = PostRepository.getInstance(mContext)

    private var mSavePost = MutableLiveData<Boolean>()
    val savePost: LiveData<Boolean> = mSavePost

    private var mPost = MutableLiveData<Post>()
    val post: LiveData<Post> = mPost

    fun save(id: Int, title: String, body: String) {
        val post = Post(id, title, body)

        if (id == 0) {
            mSavePost.value = mPostRepository.save(post)
        } else {
            mSavePost.value = mPostRepository.update(post)
        }
    }

    fun load(id: Int) {
        mPost.value = mPostRepository.get(id)
    }

}