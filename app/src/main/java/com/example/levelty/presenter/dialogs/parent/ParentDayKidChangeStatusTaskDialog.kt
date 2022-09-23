package com.example.levelty.presenter.dialogs.parent

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.levelty.R
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.domain.models.EditTask
import com.example.levelty.presenter.factories.parent.ParentDayKidTasksViewModelFactory
import com.example.levelty.presenter.utils.*
import com.example.levelty.presenter.viewmodels.parent.ParentDayKidTasksViewModel
import javax.inject.Inject


class ParentDayKidChangeStatusTaskDialog () : DialogFragment() {

    @Inject
    lateinit var parentDayKidTasksViewModelFactory: ParentDayKidTasksViewModelFactory
    val parentDayKidTasksViewModel: ParentDayKidTasksViewModel by viewModels { parentDayKidTasksViewModelFactory }

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .parentDayKidChangeStatusTaskDialogFragmentInject(this)
        super.onAttach(context)
    }

    val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val window: Window? = dialog!!.window
        window?.setGravity(Gravity.TOP or Gravity.NO_GRAVITY)
        val params: WindowManager.LayoutParams? = window?.getAttributes()
  //      params?.x = 300
        params?.y = 1100
        window?.setAttributes(params)
//        window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.fragment_day_personal_tasks_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val approveButton: com.google.android.material.textview.MaterialTextView = view.findViewById(R.id.tv_day_personal_tasks_dialog_approve)
        val declineButton: com.google.android.material.textview.MaterialTextView = view.findViewById(R.id.tv_day_personal_tasks_dialog_decline)
        val editButton: com.google.android.material.textview.MaterialTextView = view.findViewById(R.id.tv_day_personal_tasks_dialog_edit)
        val cancelButton: com.google.android.material.textview.MaterialTextView = view.findViewById(R.id.tv_day_personal_tasks_dialog_cancel)
        val taskId = arguments?.getInt(CURRENT_TASK_ID)
        val task: EditTask? = arguments?.getParcelable(CURRENT_TASK)

//        Log.d("MyLog", "task = $task")

        approveButton.setOnClickListener {
                if (taskId != null) {
                    parentDayKidTasksViewModel.updateTask(taskId, DONE_TASK_STATUS)
                }
            dismiss()
        }
//
        declineButton.setOnClickListener {
            if (taskId != null) {
                parentDayKidTasksViewModel.updateTask(taskId, REJECTED_TASK_STATUS)
            }
            dismiss()
        }

        editButton.setOnClickListener {
            val bundle = Bundle().apply {
                if (taskId != null) {
                    putInt(CURRENT_TASK_ID, taskId)
                }
                putParcelable(CURRENT_TASK, task)
            }
            navController.navigate(R.id.action_dayPersonalTasksDialogFragment_to_editTaskFragment, bundle)
            dismiss()
        }

        cancelButton.setOnClickListener {
            dismiss()
        }

    }



}