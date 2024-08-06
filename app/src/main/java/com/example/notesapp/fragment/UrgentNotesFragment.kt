package com.example.notesapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notesapp.R
import com.example.notesapp.adapter.MediumNotesAdapter
import com.example.notesapp.adapter.UrgentNotesAdapter
import com.example.notesapp.databinding.FragmentMediumNotesBinding
import com.example.notesapp.databinding.FragmentUrgentNotesBinding
import com.example.notesapp.helper.DbHelper
import com.example.notesapp.model.NotesModel

class UrgentNotesFragment : Fragment() {
    private lateinit var binding : FragmentUrgentNotesBinding
    private lateinit var urgentNotesAdapter: UrgentNotesAdapter
    private var urgentNotesList = mutableListOf<NotesModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUrgentNotesBinding.inflate(layoutInflater,container,false)
        initRv()
        return binding.root
    }

    private fun initRv() {
        urgentNotesAdapter = UrgentNotesAdapter(urgentNotesList)
        binding.rvUrgentNotes.adapter = urgentNotesAdapter
    }
    override fun onResume() {
        val dbHelper = DbHelper(activity)
        urgentNotesList = dbHelper.readDataPriority(4)
        urgentNotesAdapter.urgentDataSetChanged(urgentNotesList)
        super.onResume()
    }
}