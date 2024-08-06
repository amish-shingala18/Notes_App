package com.example.notesapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notesapp.R
import com.example.notesapp.adapter.AllNotesAdapter
import com.example.notesapp.adapter.LowNotesAdapter
import com.example.notesapp.databinding.FragmentAllNotesBinding
import com.example.notesapp.databinding.FragmentLowNotesBinding
import com.example.notesapp.helper.DbHelper
import com.example.notesapp.model.NotesModel

class LowNotesFragment : Fragment() {
    private lateinit var binding : FragmentLowNotesBinding
    private lateinit var lowNotesAdapter: LowNotesAdapter
    private var lowNotesList = mutableListOf<NotesModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLowNotesBinding.inflate(layoutInflater,container,false)
        initRv()
        return binding.root
    }
    private fun initRv() {
        lowNotesAdapter = LowNotesAdapter(lowNotesList)
        binding.rvLowNotes.adapter = lowNotesAdapter
    }
    override fun onResume() {
        val dbHelper = DbHelper(activity)
        lowNotesList = dbHelper.readDataPriority(1)
        lowNotesAdapter.lowDataSetChanged(lowNotesList)
        super.onResume()
    }
}