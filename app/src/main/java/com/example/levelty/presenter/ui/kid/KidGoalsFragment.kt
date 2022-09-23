package com.example.levelty.presenter.ui.kid

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.levelty.R
import com.example.levelty.databinding.FragmentKidGoalsBinding
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.domain.models.GoalsItem
import com.example.levelty.presenter.adapters.kid.KidGoalsWheelAdapter
import com.example.levelty.presenter.adapters.kid.TaskPickerLayoutManager
import com.example.levelty.presenter.factories.kid.KidGoalsFragmentViewModelFactory
import com.example.levelty.presenter.utils.CURRENT_KID_COINS
import com.example.levelty.presenter.utils.CURRENT_KID_LEVEL
import com.example.levelty.presenter.utils.CURRENT_KID_NAME
import com.example.levelty.presenter.viewmodels.kid.KidGoalsFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
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

        val allTasksCount = 46   // TODO Get real data
        val quantityTaskForGoal = 20  // TODO Get real data

        val kidName = binding.tvKidGoalsFragmentKidName
        val kidLevel = binding.tvKidGoalsFragmentKidLevel
        val kidCoins = binding.tvKidGoalsFragmentCoinsNumber

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        kidName.text = sharedPref?.getString(CURRENT_KID_NAME, "")
        kidLevel.text = "Level ${sharedPref?.getInt(CURRENT_KID_LEVEL, 0)}"
        kidCoins.text = sharedPref?.getInt(CURRENT_KID_COINS, 0).toString()


        val progressText = binding.tvKidGoalsFragmentProgressText
        val progressView = binding.pbKidGoalsFragmentProgressView
        val goalsRV = binding.rvKidGoalsFragmentTasksList
        val kidAvatar = binding.ivKidGoalsFragmentAvatar
        var progressTextValue = ""




        val bottomNavigation: BottomNavigationView = binding.bottomAppBarKidGoalsFragment
        val goalPickerLayoutManager =
            TaskPickerLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

//        getKidBottomNavigationBar(bottomNavigation, view)
        bottomNavigation.selectedItemId = R.id.kid_goals
        getBottonNavigation(bottomNavigation)
        bottomNavigation.itemIconTintList = null

        kidGoalsFragmentViewModel.getGoals()
        kidGoalsFragmentViewModel.goals.observe(this.viewLifecycleOwner){ allGoals ->

            val kidGoalsWheelAdapter =
                KidGoalsWheelAdapter(allGoals, goalsRV)
            //         val dayKidDetailTaskFragmentTasksAdapter = DayKidDetailTaskFragmentTasksAdapter(it)
            goalsRV.onFlingListener = null
            val tasksSnapHelper: SnapHelper = LinearSnapHelper()
            tasksSnapHelper.attachToRecyclerView(goalsRV)

            if (allGoals.size > 1) {
                goalsRV.layoutManager = goalPickerLayoutManager
            }

            goalsRV.adapter = kidGoalsWheelAdapter

            lifecycleScope.launchWhenCreated {
                delay(100)
                goalsRV.scrollToPosition(0)
            }

//            val unexecutedGoalsCount = allGoals.map { goalsItem -> goalsItem.status }.sortedBy { s -> "done" }.size

            val taskToNextGoal = allTasksCount%quantityTaskForGoal
            if (taskToNextGoal>0){
                progressTextValue = "${quantityTaskForGoal-taskToNextGoal} tasks to the next Level"

                progressText.text = progressTextValue
                progressView.max = quantityTaskForGoal
                progressView.progress = taskToNextGoal
            }

            kidGoalsWheelAdapter.goalCardListener = object : KidGoalsWheelAdapter.GoalCardListener{
                override fun onChangeButtonClick(goal: GoalsItem) {
                    Log.d("MyLog", "goal = ${goal.title}")
                    val bundle = Bundle()
                    bundle.putString(TASK_PROGRESS_TEXT, progressTextValue)
                    bundle.putInt(TASK_COUNT, quantityTaskForGoal)
                    bundle.putInt(TASK_PROGRESS, taskToNextGoal)
                    findNavController().navigate(R.id.action_kidGoalsFragment_to_kidSetGoalFragment, bundle)

                }

                override fun onGetButtonClick(goal: GoalsItem) {
                    goal.id?.let { kidGoalsFragmentViewModel.completeGoal(it) } // TODO меняем статус цели, пропускаем по цепочке, получаем реквест, меняем итем в листе, переходим на экран поздравления
                    findNavController().navigate(R.id.action_kidGoalsFragment_to_kidSuccessChooseGoalDialog)
                }

                override fun onCancelButtonClick(goal: GoalsItem) {
                    Log.d("MyLog", "Cancel button")  // TODO удаляем из списка целей данную цель и создаем карточку со статусом выбора новой цели
                }

                override fun onChooseButtonClick(goal: GoalsItem) {
                    val bundle = Bundle()
                    bundle.putString(TASK_PROGRESS_TEXT, progressTextValue)
                    bundle.putInt(TASK_COUNT, quantityTaskForGoal)
                    bundle.putInt(TASK_PROGRESS, taskToNextGoal)
                    findNavController().navigate(R.id.action_kidGoalsFragment_to_kidSetGoalFragment, bundle)
                }

            }


        }






    }

    private fun getBottonNavigation(bottomNavigation: BottomNavigationView) {
        bottomNavigation.setOnItemSelectedListener { itemMenu ->
            when (itemMenu.itemId) {
                R.id.kid_tasks -> {
                    findNavController().navigate(R.id.kidDayTasksFragment)
                    true
                }
                R.id.kid_profile -> {
                    findNavController().navigate(R.id.kidProfileFragment)
                    true
                }
                R.id.kid_goals -> {
                    findNavController().navigate(R.id.kidGoalsFragment)
                    true
                }
                else -> false
            }

        }
    }

    private fun getUnexecutedGoals(goalsList: List<GoalsItem>): Int {
        return goalsList.map { goalsItem -> goalsItem.status }.sortedBy { s -> "done" }.size
    }

    companion object {
        val TASK_COUNT = "task count"
        val TASK_PROGRESS = "task progress"
        val TASK_PROGRESS_TEXT = "task progress text"
    }
}