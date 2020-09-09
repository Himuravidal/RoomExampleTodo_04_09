package com.crisspian.roomexampletodo_04_09

import androidx.lifecycle.LiveData
import com.crisspian.roomexampletodo_04_09.database.Task
import com.crisspian.roomexampletodo_04_09.database.TaskDao

class TaskRepository(private val mTaskDao: TaskDao) {

    // Este value va a contener todos los datos de la bbdd
    val listAllTask : LiveData<List<Task>> = mTaskDao.getAllTaskFromDb()

    //Esta funcion va a insertar la info.
    suspend fun insertTask(mTask: Task) {
        mTaskDao.insertOneTask(mTask)
    }

    //Este funcion delete All
    suspend fun deleteAll() {
        mTaskDao.deleteAllTask()
    }

    fun getOnLiveDataTaskByID(id: Int) : LiveData<Task> {
        return mTaskDao.getOneTaskByID(id)
    }

}