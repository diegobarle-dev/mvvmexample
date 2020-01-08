package uk.co.diegobarle.backend.database.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import uk.co.diegobarle.backend.database.entities.Comment
import uk.co.diegobarle.backend.database.entities.CommentRep

class CommentVM (application: Application) : AndroidViewModel(application) {

    private var repository:CommentRep = CommentRep(application)

    fun getMessages() = repository.getComments()

    fun setMessage(comment: Comment) { repository.setComment(comment)}

}