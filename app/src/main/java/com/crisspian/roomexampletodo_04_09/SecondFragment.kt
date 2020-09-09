package com.crisspian.roomexampletodo_04_09

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.crisspian.roomexampletodo_04_09.database.Task
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    lateinit var mViewModel: TaskViewModel
     var idTask: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        arguments?.let {
            idTask = it.getInt("id")
            Log.d("OB", idTask.toString())
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        idTask?.let {
            mViewModel.getOneTaskByID(it).observe(viewLifecycleOwner, Observer { t ->
                Log.d("OBJECT1", it.toString())
                    checkBox.isChecked = t.completeTask
                    editTexTask.setText(t.task)
            })
        }

        saveBtn.setOnClickListener {
            val textTask = editTexTask.text.toString()
            val checkBox = checkBox.isChecked
            if (textTask.isNotEmpty()){
                val mTask = Task(task = textTask, completeTask = checkBox)
                mViewModel.insertTask(mTask)
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
        }
    }
}