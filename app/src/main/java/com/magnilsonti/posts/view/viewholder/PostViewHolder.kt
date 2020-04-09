package com.magnilsonti.posts.view.viewholder

import android.app.AlertDialog
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.magnilsonti.posts.R
import com.magnilsonti.posts.service.model.Post
import com.magnilsonti.posts.view.listener.PostListener
import kotlinx.android.synthetic.main.row_post.view.*

class PostViewHolder(itemView: View, private val listener: PostListener) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(post: Post) {

        val textTitle = itemView.text_title
        val textBody = itemView.text_body
        textTitle.text = post.title
        textBody.text = post.body

        textTitle.setOnClickListener {
            listener.onClick(post.id)
        }
        textBody.setOnClickListener {
            listener.onClick(post.id)
        }

        textTitle.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle(R.string.remocao_post)
                .setMessage(R.string.deseja_remover)
                .setPositiveButton(R.string.remover) { _, _ ->
                    listener.onDelete(post.id)
                }
                .setNeutralButton(R.string.cancelar, null)
                .show()
            true
        }

    }
}