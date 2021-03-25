package com.kjstudios.visitorlogger.model

class VisitorRepository(private val visitorDao: VisitorDao) {

    val allVisitors = visitorDao.getAllVisitors()

    suspend fun addVisitor(visitor: Visitor){
        visitorDao.addVisitor(visitor)
    }

    suspend fun deleteVisitor(visitor: Visitor){
        visitorDao.deleteVisitor(visitor)
    }

}