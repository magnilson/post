package com.magnilsonti.posts.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.magnilsonti.posts.R
import com.magnilsonti.posts.service.model.Post
import com.magnilsonti.posts.view.listener.PostListener
import com.magnilsonti.posts.view.viewholder.PostViewHolder

class PostAdapter : RecyclerView.Adapter<PostViewHolder>() {

    private var mPostList: List<Post> = arrayListOf()
    private lateinit var mListener: PostListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_post, parent, false)
        return PostViewHolder(item, mListener)
    }


    override fun getItemCount(): Int {
        return mPostList.count()
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(mPostList[position])
    }

    fun updatePosts(list: List<Post>) {
        mPostList = list
        notifyDataSetChanged()
    }

    fun attachListener(listener: PostListener) {
        mListener = listener
    }

}