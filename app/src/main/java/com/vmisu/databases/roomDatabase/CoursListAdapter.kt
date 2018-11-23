package com.vmisu.databases.roomDatabase



import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vmisu.databases.R


class CoursListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<CoursListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var courses = emptyList<Cours>() // Cached copy of words

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = courses[position]
        holder.wordItemView.text = current.cours
    }

    internal fun setWords(cours: List<Cours>) {
        this.courses = cours
        notifyDataSetChanged()
    }

    override fun getItemCount() = courses.size
}


