package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat.getColor
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.notesapp.activity.AddNotesActivity
import com.example.notesapp.activity.NotesSettingActivity
import com.example.notesapp.activity.RecycleBinActivity
import com.example.notesapp.adapter.NotesTabAdapter
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.helper.SharedHelper
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var sharedHelper = SharedHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val notesTabAdapter = NotesTabAdapter(this)
        binding.vpNotes.adapter = notesTabAdapter
        tabLayout()
        startTheme()
        initClick()
        val theme = this.let { sharedHelper.getTheme(it) }
        binding.msTheme.isChecked = theme
        binding.msTheme.setOnCheckedChangeListener { _, isChecked ->
            addTheme(isChecked)
            sharedHelper.setTheme(this,isChecked)

        }
    }
    private fun tabLayout() {
        TabLayoutMediator(binding.tbNotes, binding.vpNotes) { tab, position ->
            when (position) {
                0 ->tab.text = "All"
                1 ->tab.text = "Low"
                2 ->tab.text = "Medium"
                3 ->tab.text = "High"
                4 ->tab.text = "Urgent"

            }
        }.attach()
        binding.vpNotes.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        binding.tbNotes.setSelectedTabIndicatorColor(
                            getColor(
                                this@MainActivity,
                                R.color.white
                            )
                        )
                        //binding.tbNotes.setTabTextColors(0,R.color.white)
                    }
                    1 -> {
                        binding.tbNotes.setSelectedTabIndicatorColor(
                            getColor(
                                this@MainActivity,
                                R.color.green
                            )
                        )
                        //binding.tbNotes.setTabTextColors(0,R.color.green)
                    }
                    2 -> {
                        binding.tbNotes.setSelectedTabIndicatorColor(
                            getColor(
                                this@MainActivity,
                                R.color.amber
                            )
                        )
                        //binding.tbNotes.setTabTextColors(0,R.color.amber)
                    }
                    3 -> {
                        binding.tbNotes.setSelectedTabIndicatorColor(
                            getColor(
                                this@MainActivity,
                                R.color.orange
                            )
                        )
                        //binding.tbNotes.setTabTextColors(0,R.color.orange)
                    }
                    4 -> {
                        binding.tbNotes.setSelectedTabIndicatorColor(
                            getColor(
                                this@MainActivity,
                                R.color.red
                            )
                        )
                        //binding.tbNotes.setTabTextColors(0,R.color.red)
                    }
                }
            }
        })
    }

    private fun initClick() {
        binding.menu.setOnClickListener {
            binding.main.openDrawer(GravityCompat.START)
        }
        binding.addNote.setOnClickListener {
            val intent = Intent(this, AddNotesActivity::class.java)
            startActivity(intent)
        }
        binding.imgSetting.setOnClickListener {
            startActivity(Intent(this, NotesSettingActivity::class.java))
        }
        binding.llRecycleBin.setOnClickListener {
            val intent = Intent(this@MainActivity, RecycleBinActivity::class.java)
            startActivity(intent)
        }
    }
    private fun addTheme(theme:Boolean){
        if (theme){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
    private fun startTheme(){
        val theme = sharedHelper.getTheme(this)
        if (theme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}