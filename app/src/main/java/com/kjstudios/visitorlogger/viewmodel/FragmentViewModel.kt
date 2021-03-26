package com.kjstudios.visitorlogger.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kjstudios.visitorlogger.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentViewModel(application: Application) : AndroidViewModel(application) {

    private var deletedVisitor: Visitor? = null
    val allVisitors: LiveData<List<Visitor>>
    private val visitorRepository: VisitorRepository
    private val userRepository: UserRepository

    companion object {
        val defaultAdmin = User("admin@abc.com", "abc", "admin", -1)
        val defaultSecurity = User("security@abc.com", "abc", "security", -1)
    }

    init {
        val database = AppDatabase.initialiseDatabase(application)
        val visitorDao = database.getVisitorDao()
        val userDao = database.getUserDao()
        visitorRepository = VisitorRepository(visitorDao)
        userRepository = UserRepository(userDao)
        addUser(defaultAdmin)
        addUser(defaultSecurity)
        allVisitors = visitorRepository.allVisitors
    }

    fun addVisitor(visitor: Visitor) {
        viewModelScope.launch(Dispatchers.IO) {
            visitorRepository.addVisitor(visitor)
        }
    }

    fun deleteVisitor(visitor: Visitor) {
        viewModelScope.launch(Dispatchers.IO) {
            deletedVisitor = visitor
            visitorRepository.deleteVisitor(visitor)
        }
    }

    fun restoreVisitor() {
        if (deletedVisitor != null) {
            addVisitor(deletedVisitor!!)
            deletedVisitor = null
        }
    }

    suspend fun getLoggedInUser(): User? {
        return userRepository.getLoggedInUser()
    }

    suspend fun loginUser(email:String,password:String):String? {
        return userRepository.logInUser(email,password)
    }

    fun logoutUser() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.logoutUser()
        }
    }

    private fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteUser(user)
        }
    }


}