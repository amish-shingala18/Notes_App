package com.example.notesapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notesapp.R
import com.example.notesapp.adapter.HighNotesAdapter
import com.example.notesapp.adapter.MediumNotesAdapter
import com.example.notesapp.databinding.FragmentHighNotesBinding
import com.example.notesapp.databinding.FragmentMediumNotesBinding
import com.example.notesapp.helper.DbHelper
import com.example.notesapp.model.NotesModel

class HighNotesFragment : Fragment() {
    private lateinit var binding : FragmentHighNotesBinding
    private lateinit var highNotesAdapter: HighNotesAdapter
    private var highNotesList = mutableListOf<NotesModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHighNotesBinding.inflate(layoutInflater,container,false)
        initRv()
        return binding.root
    }

    private fun initRv() {
        highNotesAdapter = HighNotesAdapter(highNotesList)
        binding.rvHighNotes.adapter = highNotesAdapter
    }
    override fun onResume() {
        val dbHelper = DbHelper(activity)
        highNotesList = dbHelper.readDataPriority(3)
        highNotesAdapter.highDataSetChanged(highNotesList)
        super.onResume()
    }
}