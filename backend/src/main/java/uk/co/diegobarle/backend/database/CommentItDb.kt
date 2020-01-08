package uk.co.diegobarle.backend.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uk.co.diegobarle.backend.database.entities.Comment
import uk.co.diegobarle.backend.database.entities.CommentDao

@Database(entities = [Comment::class], version = 1, exportSchema = false)
abstract class CommentItDb : RoomDatabase() {

    abstract fun commentDao(): CommentDao

    companion object {

        @Volatile
        private var INSTANCE: CommentItDb? = null

        fun getDatabase(context: Context): CommentItDb? {
            if (INSTANCE == null) {
                synchronized(CommentItDb::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            CommentItDb::class.java, "comment_it_db"
                        ).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}