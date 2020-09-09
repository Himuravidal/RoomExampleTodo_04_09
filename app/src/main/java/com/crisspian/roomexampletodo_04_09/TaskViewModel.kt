package com.crisspian.roomexampletodo_04_09

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.crisspian.roomexampletodo_04_09.database.Task
import com.crisspian.roomexampletodo_04_09.database.TaskDatabase
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository
    val allTask: LiveData<List<Task>>


    init {
        val taskDao = TaskDatabase.getDatabase(application).getTaskDao()
        repository = TaskRepository(taskDao)
        allTask = repository.listAllTask
    }

    fun insertTask(task: Task) = viewModelScope.launch {
        repository.insertTask(task)
    }

    //delete all task from database
      fun deleteAllTask() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun getOneTaskByID(id: Int): LiveData<Task> {
        Log.d("OBJViewModel", repository.getOnLiveDataTaskByID(id).value.toString())
        return repository.getOnLiveDataTaskByID(id)
    }


}