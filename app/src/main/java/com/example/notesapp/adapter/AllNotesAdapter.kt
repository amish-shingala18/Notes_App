package com.example.notesapp.adapter
import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.activity.AddNotesActivity
import com.example.notesapp.databinding.NotesSampleBinding
import com.example.notesapp.helper.DbHelper
import com.example.notesapp.interfaces.NotesInterface
import com.example.notesapp.model.NotesModel
class AllNotesAdapter(private var list: MutableList<NotesModel>) :
    RecyclerView.Adapter<AllNotesAdapter.AllNotesViewHolder>() {
    class AllNotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = NotesSampleBinding.bind(itemView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllNotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_sample,parent,false)
        return AllNotesViewHolder(view)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: AllNotesViewHolder, position: Int) {
        if(list[position].notesPriority==1){
            holder.binding.lnrPriority.setBackgroundColor(holder.itemView.context.getColor(R.color.green))
        }
        else if(list[position].notesPriority==2){
            holder.binding.lnrPriority.setBackgroundColor(holder.itemView.context.getColor(R.color.amber))        }

        else if(list[position].notesPriority==3){
            holder.binding.lnrPriority.setBackgroundColor(holder.itemView.context.getColor(R.color.orange))
        }
        else if(list[position].notesPriority==4){
            holder.binding.lnrPriority.setBackgroundColor(holder.itemView.context.getColor(R.color.red))
        }

        holder.binding.txtSampleTitle.text = list[position].notesTitle
        holder.binding.txtSampleDescription.text = list[position].notesDescription
        holder.binding.txtSampleDate.text = list[position].notesDate
        holder.binding.clNotes.setOnClickListener {
            val intent = Intent(holder.itemView.context,AddNotesActivity::class.java)
            intent.putExtra("title",list[position].notesTitle)
            intent.putExtra("description",list[position].notesDescription)
            intent.putExtra("id",list[position].id)
            holder.itemView.context.startActivity(intent)
        }
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
    fun dataSetChanged(l1:MutableList<NotesModel>){
        list = l1
        notifyDataSetChanged()
    }
}
