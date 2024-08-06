package com.example.notesapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notesapp.R
import com.example.notesapp.adapter.AllNotesAdapter
import com.example.notesapp.adapter.LowNotesAdapter
import com.example.notesapp.adapter.RecycleBinAdapter
import com.example.notesapp.databinding.ActivityRecycleBinBinding
import com.example.notesapp.helper.DbHelper
import com.example.notesapp.model.NotesModel

class RecycleBinActivity : AppCompatActivity() {
    lateinit var binding : ActivityRecycleBinBinding
    private lateinit var recycleAdapter : RecycleBinAdapter
    private var recycleNotesList = mutableListOf<NotesModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        binding = ActivityRecycleBinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initRv()
    }
    private fun initRv(){
        recycleAdapter = RecycleBinAdapter(recycleNotesList)
        binding.rvRecycle.adapter = recycleAdapter
    }
    override fun onResume() {
        val dbHelper = DbHelper(this)
        recycleNotesList = dbHelper.restoreRead()
        recycleAdapter.restoreDataSetChanged(recycleNotesList)
        super.onResume()
    }
}