package com.kjstudios.visitorlogger.model

import androidx.room.*

@Dao
interface UserDao {

    @Query("select * from users where  email= :email and password=:password")
    suspend fun getUser(email: String,password: String): User?

    @Query("select * from users where isLoggedIn = 1")
    suspend fun getLoggedInUser(): User?

    @Update
    suspend fun logInUser(user: User)

    @Update
    suspend fun logoutUser(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

}
