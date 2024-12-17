package com.allan.gibus.database

import androidx.lifecycle.LiveData
import com.allan.gibus.model.Note

interface DatabaseRepository {
    val readAll: LiveData<List<Note>>

    suspend fun create(note: Note,onSuccess:() -> Unit)

    suspend fun update(note: Note,onSuccess:() -> Unit)

    suspend fun delete(note: Note,onSuccess:() -> Unit)


    fun signOut(){
    }

    fun connectToDatabase(onSuccess: () -> Unit,onFail: (String) -> Unit){}
}