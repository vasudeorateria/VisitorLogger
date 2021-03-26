package com.kjstudios.visitorlogger.model

class UserRepository(private val userDao: UserDao) {

    suspend fun logInUser(email: String, password: String): String? {
        val existingUser = userDao.getUser(email, password)
        if (existingUser != null) {
            existingUser.isLoggedIn = 1
            userDao.logoutUser(existingUser)
        }
        return existingUser?.module
    }

    suspend fun logoutUser() {
        val loggedInUser = getLoggedInUser()
        loggedInUser!!.isLoggedIn = -1
        userDao.logoutUser(loggedInUser)
    }

    suspend fun getLoggedInUser(): User? {
        return userDao.getLoggedInUser()
    }

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

}