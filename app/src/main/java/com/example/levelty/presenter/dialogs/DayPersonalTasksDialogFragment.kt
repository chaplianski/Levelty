package com.example.levelty.presenter.dialogs

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.levelty.R
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.domain.models.Task
import com.example.levelty.presenter.factories.parent.ParentDayKidTasksViewModelFactory
import com.example.levelty.presenter.viewmodels.parent.ParentDayKidTasksViewModel
import javax.inject.Inject


class DayPersonalTasksDialogFragment () : DialogFragment() {


    @Inject
    lateinit var parentDayKidTasksViewModelFactory: ParentDayKidTasksViewModelFactory
    val parentDayKidTasksViewModel: ParentDayKidTasksViewModel by viewModels { parentDayKidTasksViewModelFactory }

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .dayPersonalTasksDialogInject(this)
        super.onAttach(context)
    }


    val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val window: Window? = dialog!!.window
        window?.setGravity(Gravity.TOP or Gravity.LEFT)
        val params: WindowManager.LayoutParams? = window?.getAttributes()
  //      params?.x = 300
        params?.y = 1100
        window?.setAttributes(params)

        return inflater.inflate(R.layout.fragment_day_personal_tasks_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

   //     val viewModel = DayPersonalTasksFragmentViewModel by viewModels<> {  }
  //      val viewModel = ViewModelProvider(requireActivity()).get(DayPersonalTasksFragmentViewModel::class.java)

        val approveButton: com.google.android.material.textview.MaterialTextView = view.findViewById(R.id.tv_day_personal_tasks_dialog_approve)
        val declineButton: com.google.android.material.textview.MaterialTextView = view.findViewById(R.id.tv_day_personal_tasks_dialog_decline)
        val editButton: com.google.android.material.textview.MaterialTextView = view.findViewById(R.id.tv_day_personal_tasks_dialog_edit)
        val cancelButton: com.google.android.material.textview.MaterialTextView = view.findViewById(R.id.tv_day_personal_tasks_dialog_cancel)
//        val navController = Navigation.findNavController(view)
        val task = arguments?.getParcelable<Task>("current task")
        Log.d("MyLog", "task = $task")

        approveButton.setOnClickListener {
            task?.taskStatus = "Approval"
            if (task != null) {
                parentDayKidTasksViewModel.updateTask(task)
            }

//            if (task != null) {
//                viewModel.updateTask(task)
//            }
            dismiss()
        }
//
        declineButton.setOnClickListener {
            task?.taskStatus = "Decline"
            if (task != null) {
                parentDayKidTasksViewModel.updateTask(task)
            }
            dismiss()
        }

        editButton.setOnClickListener {
            val bundle = Bundle().apply {
                putParcelable("current task", task)
            }
            navController.navigate(R.id.action_dayPersonalTasksDialogFragment_to_editTaskFragment, bundle)
            dismiss()
        }

        cancelButton.setOnClickListener {
            dismiss()
        }

    }


}