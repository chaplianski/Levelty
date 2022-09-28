package com.example.levelty.presenter.ui.parent

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.domain.models.GoalsItem
import com.example.levelty.presenter.adapters.parent.KidGoalsFragmentAdapter
import com.example.levelty.presenter.dialogs.parent.ParentApproveDeclineGoalDialog
import com.example.levelty.presenter.factories.parent.ParentKidGoalsFragmentViewModelFactory
import com.example.levelty.presenter.utils.CURRENT_GOAL
import com.example.levelty.presenter.utils.MY_LOG
import com.example.levelty.presenter.utils.getParentBottomNavigationBar
import com.example.levelty.presenter.viewmodels.parent.ParentKidGoalsFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject


class ParentKidsGoalsFragment : Fragment() {

    @Inject
    lateinit var parentKidGoalsFragmentViewModelFactory: ParentKidGoalsFragmentViewModelFactory
    val parentKidGoalsFragmentViewModel: ParentKidGoalsFragmentViewModel by viewModels { parentKidGoalsFragmentViewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .kidGoalsFragmentInject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kids_goals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val kidNameText: TextView = view.findViewById(R.id.tv_fragment_kid_goal_name)
        val goalsRV: RecyclerView = view.findViewById(R.id.rv_fragment_kids_goals)
        val bottomNavigation: BottomNavigationView =
            view.findViewById(R.id.bottomAppBar_parent_kids_goals_fragment)

        bottomNavigation.selectedItemId = R.id.parent_profile

        getParentBottomNavigationBar(bottomNavigation, view)
        bottomNavigation.itemIconTintList = null
        parentKidGoalsFragmentViewModel.getGoalsList()

        parentKidGoalsFragmentViewModel.goalsValue.observe(this.viewLifecycleOwner) {
            val goalAdapter = KidGoalsFragmentAdapter(it)
            goalsRV.adapter = goalAdapter
            clickItem(goalAdapter)

        }

    }

    private fun clickItem(goalAdapter: KidGoalsFragmentAdapter) {
        goalAdapter.shortOnClickListener = object : KidGoalsFragmentAdapter.ShortOnClickListener {
            override fun ShortClick(goal: GoalsItem) {
                if (goal.status != "isApproval") {
//                    val bundle = Bundle()
//                    bundle.putString("goal name", goal.title)
//                    goal.price?.let { bundle.putInt("goal value", it) }
//                    findNavController().navigate(R.id.action_kidsGoalsFragment_to_goalApproveFragment, bundle)

                    showChangeStatusGoalDialog(goal)
                }
            }

        }
        setupChangeStatusGoalApprove()
        setupChangeStatusGoalDecline()

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
    fun showChangeStatusGoalDialog(goal: Parcelable) {
        ParentApproveDeclineGoalDialog.show(parentFragmentManager, goal)
    }

    private fun setupChangeStatusGoalDecline() {
        ParentApproveDeclineGoalDialog.setupDeclineListener(parentFragmentManager, this) { goal ->
            Log.d(MY_LOG, "goalId decline = ${goal.id}")
            findNavController().navigate(R.id.action_parentKidsGoalsFragment_to_parentDeclineGoalDialog)
        }
    }

    private fun setupChangeStatusGoalApprove() {
        ParentApproveDeclineGoalDialog.setupApproveListener(parentFragmentManager, this) { goal ->
            Log.d(MY_LOG, "goalId approve = ${goal.id}")
//            goal.id?.let { goal.price?.let { it1 ->
//                parentKidGoalsFragmentViewModel.approveGoal(it,
//                    it1
//                )
//            } }


            val bundle = Bundle()
            bundle.putParcelable(CURRENT_GOAL, goal)
            findNavController().navigate(R.id.action_parentKidsGoalsFragment_to_parentGoalIsApprovedDialog, bundle)
        }
    }

}