package com.example.levelty.presenter.ui.kid

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.levelty.databinding.FragmentKidGoalsBinding
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.presenter.adapters.kid.KidGoalsWheelAdapter
import com.example.levelty.presenter.adapters.kid.TaskPickerLayoutManager
import com.example.levelty.presenter.factories.kid.KidGoalsFragmentViewModelFactory
import com.example.levelty.presenter.factories.parent.ParentKidGoalsFragmentViewModelFactory
import com.example.levelty.presenter.viewmodels.kid.KidGoalsFragmentViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject


class KidGoalsFragment : Fragment() {

    @Inject
    lateinit var kidGoalsFragmentViewModelFactory: KidGoalsFragmentViewModelFactory
    val kidGoalsFragmentViewModel: KidGoalsFragmentViewModel by viewModels { kidGoalsFragmentViewModelFactory }

    var _binding: FragmentKidGoalsBinding? = null
    val binding get() = _binding!!

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .kidGoalsFragmentInject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKidGoalsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressText = binding.tvKidGoalsFragmentProgressText
        val progressView = binding.pbKidGoalsFragmentProgressView
        val goalsRV = binding.rvKidGoalsFragmentTasksList
        val kidAvatar = binding.ivKidGoalsFragmentAvatar
        val kidName = binding.tvKidGoalsFragmentKidName
        val kidLevel = binding.tvKidGoalsFragmentKidLevel
        val kidCoins = binding.ivKidGoalsFragmentCoins
        val goalPickerLayoutManager =
            TaskPickerLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        kidGoalsFragmentViewModel.getGoals()
        kidGoalsFragmentViewModel.goals.observe(this.viewLifecycleOwner){ goals ->

            val kidGoalsWheelAdapter =
                KidGoalsWheelAdapter(goals, goalsRV)
            //         val dayKidDetailTaskFragmentTasksAdapter = DayKidDetailTaskFragmentTasksAdapter(it)
            goalsRV.onFlingListener = null
            val tasksSnapHelper: SnapHelper = LinearSnapHelper()
            tasksSnapHelper.attachToRecyclerView(goalsRV)

            if (goals.size > 1) {
                goalsRV.layoutManager = goalPickerLayoutManager
            }

            goalsRV.adapter = kidGoalsWheelAdapter

            lifecycleScope.launchWhenCreated {
                delay(100)
                goalsRV.scrollToPosition(0)
            }
        }

    }

}