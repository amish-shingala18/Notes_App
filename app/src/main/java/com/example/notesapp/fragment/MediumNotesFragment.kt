package com.example.notesapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notesapp.R
import com.example.notesapp.adapter.LowNotesAdapter
import com.example.notesapp.adapter.MediumNotesAdapter
import com.example.notesapp.databinding.FragmentLowNotesBinding
import com.example.notesapp.databinding.FragmentMediumNotesBinding
import com.example.notesapp.helper.DbHelper
import com.example.notesapp.model.NotesModel

class MediumNotesFragment : Fragment() {
    private lateinit var binding : FragmentMediumNotesBinding
    private lateinit var mediumNotesAdapter: MediumNotesAdapter
    private var mediumNotesList = mutableListOf<NotesModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMediumNotesBinding.inflate(layoutInflater,container,false)
        initRv()
        return binding.root
    }
    private fun initRv() {
        mediumNotesAdapter = MediumNotesAdapter(mediumNotesList)
        binding.rvMediumNotes.adapter = mediumNotesAdapter
    }
    override fun onResume() {
        val dbHelper = DbHelper(activity)
        mediumNotesList = dbHelper.readDataPriority(2)
        mediumNotesAdapter.mediumDataSetChanged(mediumNotesList)
        super.onResume()
    }
}