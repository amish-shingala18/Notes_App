package com.example.notesapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.notesapp.MainActivity
import com.example.notesapp.fragment.AllNotesFragment
import com.example.notesapp.fragment.HighNotesFragment
import com.example.notesapp.fragment.LowNotesFragment
import com.example.notesapp.fragment.MediumNotesFragment
import com.example.notesapp.fragment.UrgentNotesFragment

class NotesTabAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> AllNotesFragment()
            1 -> LowNotesFragment()
            2 -> MediumNotesFragment()
            3 -> HighNotesFragment()
            4 -> UrgentNotesFragment()
            else -> AllNotesFragment()
        }
    }
}