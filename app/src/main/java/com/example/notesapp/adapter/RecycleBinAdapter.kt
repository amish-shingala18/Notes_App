package com.example.notesapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.databinding.NotesSampleBinding
import com.example.notesapp.helper.DbHelper
import com.example.notesapp.model.NotesModel

class RecycleBinAdapter(private var list: MutableList<NotesModel>) : RecyclerView.Adapter<RecycleBinAdapter.RecycleBinViewHolder>() {
    class RecycleBinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = NotesSampleBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleBinViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_sample,parent,false)
        return RecycleBinViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: RecycleBinViewHolder, position: Int) {
        holder.binding.lnrPriority.setBackgroundColor(holder.itemView.context.getColor(R.color.white))
        holder.binding.txtSampleTitle.text = list[position].notesTitle
        holder.binding.txtSampleDescription.text = list[position].notesDescription
        holder.binding.txtSampleDate.text = list[position].notesDate
        holder.binding.imgSampleRestore.visibility = View.VISIBLE
        holder.binding.imgSampleRestore.setOnClickListener {
            val dbHelper = DbHelper(holder.itemView.context)
            dbHelper.insert(list[position])
            dbHelper.restoreDelete(list[position].id)
            list.removeAt(position)
            notifyDataSetChanged()
        }
        holder.binding.imgSampleDelete.setOnClickListener {
            val dbHelper = DbHelper(holder.itemView.context)
            dbHelper.restoreDelete(list[position].id)
            list.removeAt(position)
            notifyDataSetChanged()
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun restoreDataSetChanged(l1: MutableList<NotesModel>) {
        list = l1
        notifyDataSetChanged()
    }
}