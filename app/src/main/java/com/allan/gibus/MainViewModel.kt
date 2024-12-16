package com.allan.gibus

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.allan.gibus.database.room.AppRoomDatabase
import com.allan.gibus.database.room.repository.RoomRepository
import com.allan.gibus.model.Note
import com.allan.gibus.utils.REPOSITORY
import com.allan.gibus.utils.TYPE_FIREBASE
import com.allan.gibus.utils.TYPE_ROOM
import java.lang.IllegalArgumentException

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val context = application

    fun initDatabase(type: String, onSuccess: () -> Unit){
        Log.d("checkData","MainViewModel initDatabase with: $type")
        when(type){
            TYPE_ROOM ->{
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
        }
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