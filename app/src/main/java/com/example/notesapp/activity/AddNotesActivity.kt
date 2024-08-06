package com.example.notesapp.activity

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityAddNotesBinding
import com.example.notesapp.helper.DbHelper
import com.example.notesapp.model.NotesModel
import java.text.SimpleDateFormat

class AddNotesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddNotesBinding
    private var intentIds :Int=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initClick()
        keyboardDetector()
    }
    private fun initClick(){
        getDataIntent()
        binding.imgBack.setOnClickListener {
            finish()
        }
        binding.mtbgPriority.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    binding.btnLow.id -> {
                        saveOrUpdateClick(1)
                    }
                    binding.btnMedium.id -> {
                        saveOrUpdateClick(2)
                    }
                    binding.btnHigh.id -> {
                        saveOrUpdateClick(3)
                    }
                    binding.btnUrgent.id -> {
                        saveOrUpdateClick(4)
                    }
                }
            }
        }
    }
    @SuppressLint("SimpleDateFormat")
    private fun saveOrUpdateClick(priority: Int) {
        val edtTitle = binding.edtTitle.text.toString()
        val edtNote = binding.edtNote.text.toString()
        val dbHelper = DbHelper(this)
        var notesModel = NotesModel(id = intentIds, notesTitle = edtTitle, notesDescription = edtNote, notesTime = "", notesDate = "", notesPriority = priority)
        val sdf = SimpleDateFormat("d MMM ")
        val currentDate = sdf.format(System.currentTimeMillis())
        notesModel.notesDate = currentDate
        notesModel =
            NotesModel(id = intentIds, notesTitle = edtTitle, notesDescription = edtNote, notesTime = "", notesDate = currentDate, notesPriority = priority)
        if (intentIds == -1) {
            notesModel.notesPriority=priority
            dbHelper.insert(notesModel)
        } else {
            dbHelper.update(notesModel)
        }
        finish()
    }
    private fun getDataIntent() {
        val intent = intent
        val intentTitle = intent.getStringExtra("title")
        val intentDescription = intent.getStringExtra("description")
        intentIds = intent.getIntExtra("id", -1)
        binding.edtTitle.setText(intentTitle)
        binding.edtNote.setText(intentDescription)
    }
    private fun keyboardDetector(){
        binding.main.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            binding.main.getWindowVisibleDisplayFrame(rect)
            val screenHeight = binding.main.rootView.height
            val keypadHeight = screenHeight - rect.bottom

            if (keypadHeight > screenHeight * 0.15) {
                val view = binding.llPriority.layoutParams as ConstraintLayout.LayoutParams
                view.bottomMargin = keypadHeight
                binding.llPriority.layoutParams = view
            } else {
                val view = binding.llPriority.layoutParams as ConstraintLayout.LayoutParams
                view.bottomMargin = 0
                binding.llPriority.layoutParams = view
            }
        }
    }
}
