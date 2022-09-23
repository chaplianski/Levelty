package com.example.levelty.presenter.ui.kid

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.levelty.R
import com.example.levelty.databinding.FragmentKidSetGoalBinding
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.domain.models.GoalsItem
import com.example.levelty.presenter.adapters.kid.KidSetGoalsAdapter
import com.example.levelty.presenter.factories.kid.KidSetGoalFragmentViewModelFactory
import com.example.levelty.presenter.utils.CURRENT_KID_ID
import com.example.levelty.presenter.utils.getKidBottomNavigationBar
import com.example.levelty.presenter.viewmodels.kid.KidSetGoalFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject


class KidSetGoalFragment : Fragment() {

//    var _binding: FragmentKidSetGoalBinding? = null
//    val binding: FragmentKidSetGoalBinding = _binding!!
    lateinit var binding: FragmentKidSetGoalBinding

    @Inject
    lateinit var kidSetGoalFragmentViewModelFactory: KidSetGoalFragmentViewModelFactory
    val kidSetGoalFragmentViewModel: KidSetGoalFragmentViewModel by viewModels { kidSetGoalFragmentViewModelFactory }

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .kidSetGoalFragmentInject(this)
        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        return inflater.inflate(R.layout.fragment_kid_set_goal, container, false)
//        _binding = FragmentKidSetGoalBinding.inflate(layoutInflater, container, false)
//        return binding.root
        binding = FragmentKidSetGoalBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val goalsRV = binding.rvKidSetGoalFragmentGoals
        val progressText = binding.tvKidSetGoalFragmentProgressText
        val progressView = binding.pbKidSetGoalFragmentProgressView
        val backButton = binding.ivKidSetGoalFragmentBack
        val bottomNavigation = binding.bottomAppBarKidSetGoalFragment

        val progressTextValue = arguments?.getString(KidGoalsFragment.TASK_PROGRESS_TEXT, "")
        val progressMax = arguments?.getInt(KidGoalsFragment.TASK_COUNT)
        val progressCount = arguments?.getInt(KidGoalsFragment.TASK_PROGRESS)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val kidId = sharedPref?.getInt(CURRENT_KID_ID, 0)

        var currentGoal: GoalsItem? = null
        bottomNavigation.selectedItemId = R.id.kid_goals
        bottomNavigation.itemIconTintList = null
        getBottonNavigation(bottomNavigation)


        progressText.text = progressTextValue
        if (progressMax != null) {
            progressView.max = progressMax
        }
        if (progressCount != null) {
            progressView.progress = progressCount
        }

        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_kidSetGoalFragment_to_kidGoalsFragment)
        }

        goalsRV.layoutManager = GridLayoutManager(context,2)

        kidSetGoalFragmentViewModel.getGoals()
        kidSetGoalFragmentViewModel.goals.observe(this.viewLifecycleOwner){ listGoals ->

            val goalsList = mutableListOf<GoalsItem>()
            goalsList.add(GoalsItem(0, kidId,"Add new goal","0",0, "New goal",""))
            goalsList.addAll(listGoals)
            val goalAdapter = KidSetGoalsAdapter(goalsList)
            goalsRV.adapter = goalAdapter

            goalAdapter.onclickGoalListener = object : KidSetGoalsAdapter.OnClickGoalLiastener{
                override fun onClickAddGoal() {
                    findNavController().navigate(R.id.action_kidSetGoalFragment_to_kidAddNewGoalDialog)
                }

                override fun onClickGoal(goalsItem: GoalsItem) {
                    currentGoal = goalsItem
                    val bundle = Bundle()
//                    goalsItem.id?.let { bundle.putInt("goal id", it) }
                    bundle.putString("goal name", goalsItem.title)
                    findNavController().navigate(R.id.action_kidSetGoalFragment_to_kidConfirmGoalDialog, bundle)
                }

            }

            findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("new goal")
                ?.observe(
                    viewLifecycleOwner
                ) { newGoal ->
                   kidSetGoalFragmentViewModel.sendNewGoalToParent(newGoal)
                }

            findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>("goal confirm")
                ?.observe(
                    viewLifecycleOwner
                ) { goalId ->
                    currentGoal?.let { kidSetGoalFragmentViewModel.confirmGoal(it) }
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

}