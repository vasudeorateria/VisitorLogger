package com.kjstudios.visitorlogger

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdminViewModel(application: Application) : AndroidViewModel(application) {

    val allVisitors:LiveData<List<Visitor>>
    private val repository:VisitorRepository

    init{
        val visitorDao = VisitorDatabase.getDatabase(application).visitorDao()
        repository = VisitorRepository(visitorDao)
        allVisitors = repository.allVisitors
    }

    fun addVisitor(visitor: Visitor){
        viewModelScope.launch(Dispatchers.IO){
            repository.addVisitor(visitor)
        }
    }

}