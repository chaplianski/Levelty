package com.example.levelty.presenter.dialogs.kid

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.levelty.R
import com.example.levelty.databinding.FragmentKidEarnCoinsDialogBinding
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.domain.models.KidProcessedTask
import com.example.levelty.presenter.adapters.kid.KidApprovedTaskAdapter
import com.example.levelty.presenter.factories.kid.KidProfileFragmentViewModelFactory
import com.example.levelty.presenter.utils.CURRENT_CHORE_ID
import com.example.levelty.presenter.viewmodels.kid.KidProfileFragmentViewModel
import javax.inject.Inject


class KidEarnCoinsDialog : DialogFragment() {

    var _binding: FragmentKidEarnCoinsDialogBinding? = null
    val binding get() = _binding!!

    @Inject
    lateinit var kidProfileFragmentViewModelFactory: KidProfileFragmentViewModelFactory
    val kidProfileFragmentViewModel: KidProfileFragmentViewModel by activityViewModels { kidProfileFragmentViewModelFactory }

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .kidEarnCoinsDialogInject(this)
        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKidEarnCoinsDialogBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val title = binding.tvKidEarnCoinsSum
        val approvedTaskRV = binding.rvKidEarnCoinsList
        val checkButton = binding.btKidEarnCoinsCheck



        kidProfileFragmentViewModel.tasks.observe(this.viewLifecycleOwner){ allTasks ->
            val approvedTaskList = getApprovedTasks(allTasks)
            title.text = "You earned ${getEarnedValue(approvedTaskList)} coins!"

            val approvedTasksAdapter = KidApprovedTaskAdapter(approvedTaskList)
            approvedTaskRV.layoutManager = LinearLayoutManager(context)
            approvedTaskRV.adapter = approvedTasksAdapter
        }

        checkButton.setOnClickListener {
            parentFragmentManager.setFragmentResult(
                REQUEST_KEY, bundleOf(
                    KEY_RESPONSE to true))
            dismiss()
        }

    }


    fun getApprovedTasks(taskList: List<KidProcessedTask>):List<KidProcessedTask>{
        val approvedTaskList = mutableListOf<KidProcessedTask>()
        for (task in taskList){
            if (task.choreStatus == "done") approvedTaskList.add(task)
        }
        return approvedTaskList
    }

    fun getEarnedValue(taskList: List<KidProcessedTask>): Int{
        return taskList.sumOf { taskList -> taskList.cost!! }

    }
    companion object {

        val KEY_RESPONSE = "key response"

        val TAG = KidEarnCoinsDialog::class.java.simpleName
        val REQUEST_KEY = "$TAG: default request key"
        //
        fun show(manager: FragmentManager) {
            val dialogFragment = KidEarnCoinsDialog()
//            dialogFragment.arguments = bundleOf(
//                CURRENT_CHORE_ID to currentChoreId
//            )
            dialogFragment.show(manager, TAG)
        }

        fun setupListener(manager: FragmentManager, lifecycleOwner: LifecycleOwner, listener: (Boolean) -> Unit) {
            manager.setFragmentResultListener(REQUEST_KEY, lifecycleOwner, FragmentResultListener { key, result ->
                result.getBoolean(KEY_RESPONSE).let { listener.invoke(it) }
            })
        }
    }

}