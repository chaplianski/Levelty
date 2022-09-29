package com.example.levelty.presenter.ui.parent

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.domain.models.ParentProcessedTask
import com.example.levelty.presenter.adapters.parent.CategoryFragmentAdapter
import com.example.levelty.presenter.factories.parent.CategoryFragmentViewModelFactory
import com.example.levelty.presenter.utils.CURRENT_TASK
import com.example.levelty.presenter.utils.mapToEditTask
import com.example.levelty.presenter.viewmodels.parent.ParentCategoryFragmentViewModel
import javax.inject.Inject

//Для фрагментов создай общий фрагмент и используй его с вью биндинг а то получается что-то фигово чутка)
class CategoryFragment : Fragment() {

    @Inject
    lateinit var categoryFragmentViewModelFactory: CategoryFragmentViewModelFactory
    val parentCategoryFragmentViewModel: ParentCategoryFragmentViewModel by viewModels { categoryFragmentViewModelFactory }


    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .categoryFragmentInject(this)//TODO https://medium.com/@serapbercin001/how-to-use-android-injector-for-activity-and-fragment-objects-through-new-dagger-2-with-kotlin-704eb8a98c43
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)//TODO Why don't use ViewBinding?
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backButton: ImageView = view.findViewById(R.id.iv_category_fragment_back)
        val categoryRV: RecyclerView = view.findViewById(R.id.rv_category_fragment_category_list)
        val addTaskButton: ConstraintLayout = view.findViewById(R.id.cl_category_fragment_add_task)
        val currentCategory = arguments?.getString("category")

        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_categoryFragment_to_tasksFragment)
        }

        addTaskButton.setOnClickListener {
            findNavController().navigate(R.id.action_categoryFragment_to_newTaskFragment)
        }

        parentCategoryFragmentViewModel.getTasks()
        parentCategoryFragmentViewModel.tasks.observe(this.viewLifecycleOwner) { tasks ->
            Log.d("MyLog", "category = ${currentCategory}")

            val taskAdapter =
                CategoryFragmentAdapter(getTaskList(tasks.filter { it.category?.title == currentCategory }))
            categoryRV.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            categoryRV.adapter = taskAdapter
            taskAdapter.categoryClickListener =
                object : CategoryFragmentAdapter.CategoryClickListener {
                    override fun onItemClick(parentProcessedTask: ParentProcessedTask) {
                        val bundle = Bundle()
                        val editTask = parentProcessedTask.mapToEditTask()
                        bundle.putParcelable(CURRENT_TASK, editTask)
                        bundle.putString(CATEGORY_MARK, "from category fragment")
                        findNavController().navigate(
                            R.id.action_categoryFragment_to_editTaskFragment,
                            bundle
                        )
                    }

                }
        }

    }

    fun getTaskList(tasks: List<ParentProcessedTask>): List<ParentProcessedTask> {
        val taskSet = tasks.map { task -> task.title }.toSet()
        val taskList = mutableListOf<ParentProcessedTask>()
        taskSet.forEach { title ->
            for (task in tasks) {
                if (task.title == title) {
                    taskList.add(task)
                    break
                }
            }
        }
        Log.d("MyLog", "setSize = ${taskSet.size}, listSize = ${tasks.size}, listsetSize = ${taskList.size}")
        return taskList
    }

    companion object {
        const val CATEGORY_MARK = "category"
    }
}