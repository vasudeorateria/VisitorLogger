package com.kjstudios.visitorlogger.model

import androidx.room.*

@Dao
interface UserDao {

    @Query("select * from users where  email= :email ")
    suspend fun getUser(email: String): User?

    @Query("select * from users where isLoggedIn = 1 and module=:module")
    suspend fun getLoggedInUser(module:String): User?

    @Update
    suspend fun logInUser(user: User)

    @Update
    suspend fun logoutUser(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

}
