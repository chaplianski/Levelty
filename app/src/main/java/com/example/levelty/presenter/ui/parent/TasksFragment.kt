package com.example.levelty.presenter.ui.parent

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.levelty.R
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.domain.models.Task
import com.example.levelty.presenter.adapters.parent.TasksFragmentAdapter
import com.example.levelty.presenter.factories.parent.TaskFragmentViewModelFactory
import com.example.levelty.presenter.viewmodels.parent.TaskFragmentViewModel
import javax.inject.Inject


class TasksFragment : Fragment() {

    @Inject
    lateinit var tasksFragmentViewModelFactory: TaskFragmentViewModelFactory
    val tasksFragmentViewModel: TaskFragmentViewModel by viewModels {tasksFragmentViewModelFactory}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .tasksFragmentInject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskRV: RecyclerView = view.findViewById(R.id.rv_fragment_tasks_list)

        tasksFragmentViewModel.getTasksListValue()
        tasksFragmentViewModel.tasksListValue.observe(this.viewLifecycleOwner){
            val listTasks: MutableList<Task> = it as MutableList<Task>
            listTasks.add(0, Task((listTasks.size+1).toLong(), "My Task", "", 0, 0, 0, "0", "", "", 0 ))
            val tasksAdapter = TasksFragmentAdapter(listTasks.toList())
            val lm = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


//            linearLayout.setBackgroundColor(Color.BLUE);
//            linearLayout.setBackgroundResource(R.drawable.rounded);
            taskRV.layoutManager = lm
            taskRV.adapter = tasksAdapter



        }


    }


}