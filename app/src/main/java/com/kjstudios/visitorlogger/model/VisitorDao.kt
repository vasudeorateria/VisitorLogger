package com.kjstudios.visitorlogger

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface VisitorDao {

    @Query("select * from visitors order by time asc")
    fun getAllVisitors(): LiveData<List<Visitor>>

    @Query("select * from visitors where id = :visitorId ")
    fun getVisitor(visitorId: Int): Visitor

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addVisitor(visitor: Visitor)

    @Delete
    suspend fun deleteVisitor(visitor: Visitor)

}
