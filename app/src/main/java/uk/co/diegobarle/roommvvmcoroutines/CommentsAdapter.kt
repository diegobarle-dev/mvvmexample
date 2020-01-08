package uk.co.diegobarle.roommvvmcoroutines

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uk.co.diegobarle.backend.database.entities.Comment

class CommentsAdapter (private val context: Context, private val comments: List<Comment>?) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, index: Int): ViewHolder {
        val rootView = LayoutInflater.from(viewGroup.context).inflate(R.layout.view_comment, viewGroup, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return comments?.size?:0
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, index: Int) {
        viewHolder.commentText?.text = comments?.get(index)?.comment
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var commentText = itemView.findViewById<TextView?>(R.id.commentTextView)
    }

}