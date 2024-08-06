package com.example.notesapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.databinding.NotesSampleBinding
import com.example.notesapp.helper.DbHelper
import com.example.notesapp.model.NotesModel

class LowNotesAdapter(private var list: MutableList<NotesModel>) :
    RecyclerView.Adapter<LowNotesAdapter.LowNotesViewHolder>() {
    class LowNotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = NotesSampleBinding.bind(itemView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LowNotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_sample,parent,false)
        return LowNotesViewHolder(view)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: LowNotesViewHolder, position: Int) {
        holder.binding.lnrPriority.setBackgroundColor(holder.itemView.context.getColor(R.color.white))
        holder.binding.txtSampleTitle.text = list[position].notesTitle
        holder.binding.txtSampleDescription.text = list[position].notesDescription
        holder.binding.txtSampleDate.text = list[position].notesDate
        holder.binding.imgSampleDelete.setOnClickListener {
            val dbHelper = DbHelper(holder.itemView.context)
            var notesModel =list[position]
            dbHelper.restoreInsert(notesModel)
            dbHelper.delete(list[position].id)
            list.removeAt(position)
            notifyDataSetChanged()
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun lowDataSetChanged(l1: MutableList<NotesModel>) {
        list = l1
        notifyDataSetChanged()
    }
}