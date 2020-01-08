package uk.co.diegobarle.backend.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class Comment(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id:Int,

    @ColumnInfo(name = "comment")
    var comment:String

)