package com.magnilsonti.posts.service.repository

import android.content.ContentValues
import android.content.Context
import com.magnilsonti.posts.service.constants.DataBaseConstants.POST
import com.magnilsonti.posts.service.constants.DataBaseConstants.POST.FILTER.AND_ID
import com.magnilsonti.posts.service.model.Post
import java.util.*

class PostRepository private constructor(context: Context) {
    private var mPostDataBaseHelper: PostDataBaseHelper = PostDataBaseHelper(context)

    companion object {
        private lateinit var repository: PostRepository
        fun getInstance(context: Context): PostRepository {
            if (!Companion::repository.isInitialized) {
                repository = PostRepository(context)
            }
            return repository
        }
    }

    fun get(id: Int): Post? {
        var post: Post? = null

        return try {
            val db = mPostDataBaseHelper.readableDatabase
            val projection = arrayOf(
                POST.COLUMNS.TITLE,
                POST.COLUMNS.BODY
            )
            val selection = AND_ID
            val args = arrayOf(id.toString())
            db.query(
                POST.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null
            )?.use { cursor ->
                while (cursor.moveToNext()) {
                    val title = cursor.getString(cursor.getColumnIndex(POST.COLUMNS.TITLE))
                    val body = cursor.getString(cursor.getColumnIndex(POST.COLUMNS.BODY))
                    post = Post(id, title, body)
                }
            }
            post
        } catch (e: Exception) {
            post
        }
    }

    fun getAll(): List<Post> {
        val list: MutableList<Post> = ArrayList()
        return try {
            val db = mPostDataBaseHelper.readableDatabase
            val projection = arrayOf(
                POST.COLUMNS.ID,
                POST.COLUMNS.TITLE,
                POST.COLUMNS.BODY
            )
            db.query(
                POST.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )?.use { cursor ->
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(POST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(POST.COLUMNS.TITLE))
                    val body = cursor.getString(cursor.getColumnIndex(POST.COLUMNS.BODY))
                    list.add(Post(id, name, body))
                }
            }
            return list
        } catch (e: Exception) {
            return list
        }
    }

    fun save(post: Post): Boolean {
        return try {
            val db = mPostDataBaseHelper.readableDatabase
            val contentValues = ContentValues()
            contentValues.put(POST.COLUMNS.TITLE, post.title)
            contentValues.put(POST.COLUMNS.BODY, post.body)
            db.insert(POST.TABLE_NAME, null, contentValues)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(post: Post): Boolean {
        return try {
            val db = mPostDataBaseHelper.readableDatabase
            val contentValues = ContentValues()
            contentValues.put(POST.COLUMNS.TITLE, post.title)
            contentValues.put(POST.COLUMNS.BODY, post.body)
            val selection = AND_ID
            val args = arrayOf(post.id.toString())
            db.update(POST.TABLE_NAME, contentValues, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun delete(id: Int): Boolean {
        return try {
            val db = mPostDataBaseHelper.readableDatabase
            val selection = AND_ID
            val args = arrayOf(id.toString())
            db.delete(POST.TABLE_NAME, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }
}