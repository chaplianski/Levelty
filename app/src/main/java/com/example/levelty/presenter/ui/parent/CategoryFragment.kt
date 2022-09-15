package com.example.levelty.presenter.ui.parent

import android.content.Context
import android.os.Bundle
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
import com.example.levelty.presenter.viewmodels.parent.CategoryFragmentViewModel
import javax.inject.Inject


class CategoryFragment : Fragment() {

    @Inject
    lateinit var categoryFragmentViewModelFactory: CategoryFragmentViewModelFactory
    val categoryFragmentViewModel: CategoryFragmentViewModel by viewModels { categoryFragmentViewModelFactory }

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .categoryFragmentInject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backButton: ImageView = view.findViewById(R.id.iv_category_fragment_back)
        val categoryRV: RecyclerView = view.findViewById(R.id.rv_category_fragment_category_list)
        val addTaskButton: ConstraintLayout = view.findViewById(R.id.cl_category_fragment_add_task)

        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_categoryFragment_to_tasksFragment)
        }

        addTaskButton.setOnClickListener {
            findNavController().navigate(R.id.action_categoryFragment_to_newTaskFragment)
        }

        categoryFragmentViewModel.getTasks()
        categoryFragmentViewModel.tasks.observe(this.viewLifecycleOwner){ tasks ->
            val taskAdapter = CategoryFragmentAdapter(tasks)
            categoryRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            categoryRV.adapter = taskAdapter
            taskAdapter.categoryClickListener = object : CategoryFragmentAdapter.CategoryClickListener{
                override fun onItemClick(parentProcessedTask: ParentProcessedTask) {
                    val bundle = Bundle()
                    val editTask = parentProcessedTask.mapToEditTask()
                    bundle.putParcelable(CURRENT_TASK, editTask)
                    bundle.putString(CATEGORY_MARK, "from category fragment")
                    findNavController().navigate(R.id.action_categoryFragment_to_editTaskFragment, bundle)
                }

            }
        }

    }

    companion object {
        val CATEGORY_MARK = "category"
    }
}