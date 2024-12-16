package com.allan.gibus.utils

import com.allan.gibus.database.DatabaseRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"


lateinit var  REPOSITORY: DatabaseRepository

object Constants{
    object Keys{
        const val NOTE_DATABASE = "notes_database"
        const val NOTES_TABLE = "notes_table"
        const val ID ="id"
        const val NONE = "none"
        const val FIREBASE_DATABASE = "Firebase database"
        const val UPDATE = "UPDATE"
        const val DELETE = "DELETE"
        const val NAV_BACK = "NAV_BACK"
        const val EDIT_NOTE = "Edit note"
        const val EMPTY = ""
        const val TITLE = "Title"
        const val SUBTITLE = "Subtitle"
        const val UPDATE_NOTE = "Update note"

    }
    object  Screens{
        const val START_SCREEN = "start_screen"
        const val MAIN_SCREEN = "main_screen"
        const val ADD_SCREEN = "add_screen"
        const val NOTE_SCREEN = "note_screen"

    }
}