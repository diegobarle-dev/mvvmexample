package uk.co.diegobarle.backend.database.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMessage(movie: Comment)

    @Query("SELECT * from comments ORDER BY id ASC")
    fun getMessages() : LiveData<List<Comment>>

    @Query("DELETE FROM comments")
    fun deleteAll()

}