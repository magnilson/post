package com.magnilsonti.posts.service.constants

class DataBaseConstants private constructor() {
    object POST {
        const val TABLE_NAME = "Post"

        object COLUMNS {
            const val ID = "id"
            const val TITLE = "title"
            const val BODY = "body"
        }

        object FILTER {
            const val AND_ID = "${COLUMNS.ID} = ?"
        }
    }
}