package com.example.levelty.presenter.ui.parent

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.levelty.R
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.domain.models.Category
import com.example.levelty.presenter.adapters.parent.TasksFragmentAdapter
import com.example.levelty.presenter.factories.parent.TaskFragmentViewModelFactory
import com.example.levelty.presenter.utils.getParentBottomNavigationBar
import com.example.levelty.presenter.viewmodels.parent.TaskFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject


class ParentTasksFragment : Fragment() {

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
        val bottomNavigation: BottomNavigationView = view.findViewById(R.id.bottomAppBar_parent_tasks_fragment)
        bottomNavigation.selectedItemId = R.id.parent_tasks

        getParentBottomNavigationBar(bottomNavigation, view)
        bottomNavigation.itemIconTintList = null
        tasksFragmentViewModel.getTasksListValue()
        tasksFragmentViewModel.tasksListValue.observe(this.viewLifecycleOwner){ tasks ->


//            val categoriesList = mutableListOf<String>()
//            val realCategories = tasks.map { task -> task.category?.title.toString() }.toSet().toList()
//            categoriesList.add("My Task")
//            categoriesList.addAll(realCategories)

            val categoriesList = mutableListOf<Category?>()
            val realCategories = tasks.map { task -> task.category }.toSet().toList()
            categoriesList.add(Category(null, "#2D98FB", null, "My Task"))
            categoriesList.addAll(realCategories)

//            val listTasks: MutableList<Task> = it as MutableList<Task>
//            listTasks.add(0, Task((listTasks.size+1).toLong(), "My Task", "", 0, "", "0", "0", "", "", "" ,"Need approval"))
//            val tasksAdapter = TasksFragmentAdapter(listTasks.toList())


            val tasksAdapter = TasksFragmentAdapter(categoriesList.toList() as List<Category>)
            val lm = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


//            linearLayout.setBackgroundColor(Color.BLUE);
//            linearLayout.setBackgroundResource(R.drawable.rounded);
            taskRV.layoutManager = lm
            taskRV.adapter = tasksAdapter



        }


    }
//    private fun getParentBottomNavigationBar(bottomNavigation: BottomNavigationView) {
//        bottomNavigation.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.tasks -> {
//                    findNavController().navigate(R.id.tasksFragment)
//                    true
//                }
//                R.id.profile -> {
//                    findNavController().navigate(R.id.profileFragment)
//                    true
//                }
//                R.id.settings -> {
//                    findNavController().navigate(R.id.settingsFragment)
//                    true
//                }
//                else -> false
//            }
//        }
//    }

}