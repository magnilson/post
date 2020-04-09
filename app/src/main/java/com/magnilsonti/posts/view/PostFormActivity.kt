package com.magnilsonti.posts.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.magnilsonti.posts.R
import com.magnilsonti.posts.service.constants.PostConstants
import com.magnilsonti.posts.viewmodel.PostFormViewModel
import kotlinx.android.synthetic.main.activity_post_form.*

class PostFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: PostFormViewModel
    private var mPostId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_form)
        mViewModel = ViewModelProvider(this).get(PostFormViewModel::class.java)
        setListeners()
        observe()
        loadData()
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.button_save) {
            val title = edit_title.text.toString()
            val body = edit_body.text.toString()
            mViewModel.save(mPostId, title, body)
        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            mPostId = bundle.getInt(PostConstants.POSTID)
            mViewModel.load(mPostId)
        }
    }

    private fun observe() {
        mViewModel.savePost.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })

        mViewModel.post.observe(this, Observer {
            edit_title.setText(it.title)
            edit_body.setText(it.body)
        })
    }

    private fun setListeners() {
        button_save.setOnClickListener(this)
    }
}
