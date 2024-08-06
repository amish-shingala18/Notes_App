package com.example.notesapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notesapp.R
import com.example.notesapp.adapter.AllNotesAdapter
import com.example.notesapp.databinding.FragmentAllNotesBinding
import com.example.notesapp.helper.DbHelper
import com.example.notesapp.interfaces.NotesInterface
import com.example.notesapp.model.NotesModel

class AllNotesFragment : Fragment() {
    private lateinit var binding : FragmentAllNotesBinding
    private lateinit var allNotesAdapter: AllNotesAdapter
    private var allNotesList = mutableListOf<NotesModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllNotesBinding.inflate(layoutInflater,container,false)
        //notesInterface.onLongClickListener()
        initRv()
        return binding.root
    }
    private fun initRv(){
        allNotesAdapter = AllNotesAdapter(allNotesList)
        binding.rvAllNotes.adapter = allNotesAdapter
    }
    override fun onResume() {
        val dbHelper = DbHelper(activity)
        allNotesList = dbHelper.read()
        allNotesAdapter.dataSetChanged(allNotesList)
        super.onResume()
    }
}
