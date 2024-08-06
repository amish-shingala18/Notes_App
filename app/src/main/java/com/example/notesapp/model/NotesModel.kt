package com.example.notesapp.model

data class NotesModel(var id:Int=0,
                      var notesTitle:String,
                      var notesDescription:String,
                      var notesTime:String,
                      var notesDate:String,
                      var notesPriority:Int=0)