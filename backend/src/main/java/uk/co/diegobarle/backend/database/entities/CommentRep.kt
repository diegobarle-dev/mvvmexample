package uk.co.diegobarle.backend.database.entities

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uk.co.diegobarle.backend.database.CommentItDb
import kotlin.coroutines.CoroutineContext

class CommentRep(application: Application) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var commentDao: CommentDao?

    init {
        val db = CommentItDb.getDatabase(application)
        commentDao = db?.commentDao()
    }

    fun getComments() = commentDao?.getMessages()

    fun setComment(comment: Comment) {
        launch  { setCommentBG(comment) }
    }

    private suspend fun setCommentBG(comment: Comment){
        withContext(Dispatchers.IO){
            commentDao?.setMessage(comment)
        }
    }

}