package com.kjstudios.visitorlogger.model

class UserRepository(private val userDao: UserDao) {

    suspend fun logInUser(user: User) {
        val existingUser = userDao.getUser(user.email)
        if (user == existingUser) {
            user.isLoggedIn = 1
            userDao.logInUser(user)
        }
    }

    suspend fun logoutUser(module: String) {
        val lu = getLoggedInUser(module)
        lu!!.isLoggedIn = -1
        userDao.logoutUser(lu)
    }

    suspend fun getLoggedInUser(module: String): User? {
        return userDao.getLoggedInUser(module)
    }

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

}