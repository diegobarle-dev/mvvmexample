package uk.co.diegobarle.roommvvmcoroutines

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import kotlinx.android.synthetic.main.activity_main.*
import uk.co.diegobarle.backend.database.entities.Comment
import uk.co.diegobarle.backend.database.viewmodels.CommentVM

class MainActivity : AppCompatActivity() {

    companion object{
        private const val DEFAULT_MSG_LENGTH_LIMIT = 100
    }

    private lateinit var commentVM: CommentVM
    lateinit var adapter: CommentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        commentVM = ViewModelProviders.of(this).get(CommentVM::class.java)
        commentVM.getMessages()?.observe(this, Observer<List<Comment>> { this.setupComments(it) })
        setupViews()
    }

    private fun setupViews(){
        commentEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                sendButton.isEnabled = charSequence.toString().trim { it <= ' ' }.isNotEmpty()
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        commentEditText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT))
    }

    fun sendComment(view: View){
        val message = Comment(0, commentEditText.text.toString())
        commentVM.setMessage(message)
        commentEditText.setText("")
    }

    private fun setupComments(comments: List<Comment>?){
        adapter = CommentsAdapter(this, comments)
        commentsRecyclerView.adapter = adapter
    }

}
