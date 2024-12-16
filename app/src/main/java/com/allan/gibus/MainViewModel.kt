package com.allan.gibus

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.allan.gibus.model.Note
import com.allan.gibus.utils.TYPE_FIREBASE
import com.allan.gibus.utils.TYPE_ROOM
import java.lang.IllegalArgumentException

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val readTest: MutableLiveData<List<Note>> by lazy{
        MutableLiveData<List<Note>>()
    }

    val dbType: MutableLiveData<String> by lazy{
        MutableLiveData<String>(TYPE_ROOM)
    }

    init{
        readTest.value =
            when(dbType.value){
                TYPE_ROOM -> {
                    listOf<Note>(
                        Note(title = "Note 1", subtitle = "Subtitle for note 1"),
                        Note(title = "Note 2", subtitle = "Subtitle for note 2"),
                        Note(title = "Note 3", subtitle = "Subtitle for note 3"),
                        Note(title = "Note 4", subtitle = "Subtitle for note 4")
                    )
                }
                TYPE_FIREBASE -> listOf()
                else -> listOf()
            }
    }

    fun initDatabase(type: String){
        dbType.value = type
        Log.d("checkData","MainViewModel initDatabase with: $type")
    }
}
//для корректности создания
class MainViewModelFactory(private val application:Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel Class")
    }
}