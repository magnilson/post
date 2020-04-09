package com.magnilsonti.posts.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.magnilsonti.posts.R
import com.magnilsonti.posts.service.constants.PostConstants
import com.magnilsonti.posts.view.adapter.PostAdapter
import com.magnilsonti.posts.view.listener.PostListener
import com.magnilsonti.posts.viewmodel.PostViewModel

class PostsFragment : Fragment() {

    private lateinit var mViewModel: PostViewModel
    private val mAdapter: PostAdapter = PostAdapter()
    private lateinit var mListener: PostListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_all, container, false)
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_posts)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter
        observe()
        mListener = object : PostListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, PostFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(PostConstants.POSTID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }
            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load()
            }
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mListener)
        mViewModel.load()
    }

    private fun observe() {
        mViewModel.postList.observe(viewLifecycleOwner, Observer {
            mAdapter.updatePosts(it)
        })
    }
}
