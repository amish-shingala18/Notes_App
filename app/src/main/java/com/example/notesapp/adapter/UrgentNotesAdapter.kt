package com.example.notesapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.databinding.NotesSampleBinding
import com.example.notesapp.model.NotesModel

class UrgentNotesAdapter(private var list: MutableList<NotesModel>) : RecyclerView.Adapter<UrgentNotesAdapter.UrgentNotesViewHolder>() {
    class UrgentNotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = NotesSampleBinding.bind(itemView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UrgentNotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_sample,parent,false)
        return UrgentNotesViewHolder(view)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: UrgentNotesViewHolder, position: Int) {
        holder.binding.lnrPriority.setBackgroundColor(holder.itemView.context.getColor(R.color.white))

        holder.binding.txtSampleTitle.text = list[position].notesTitle
        holder.binding.txtSampleDescription.text = list[position].notesDescription
        holder.binding.txtSampleDate.text = list[position].notesDate

    }
    @SuppressLint("NotifyDataSetChanged")
    fun urgentDataSetChanged(l1: MutableList<NotesModel>) {
        list = l1
        notifyDataSetChanged()
    }
}