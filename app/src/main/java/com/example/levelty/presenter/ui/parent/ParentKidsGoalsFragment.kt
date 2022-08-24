package com.example.levelty.presenter.ui.parent

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.domain.models.Goal
import com.example.levelty.presenter.adapters.parent.KidGoalsFragmentAdapter
import com.example.levelty.presenter.factories.parent.ParentKidGoalsFragmentViewModelFactory
import com.example.levelty.presenter.viewmodels.parent.ParentKidGoalsFragmentViewModel
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

        parentKidGoalsFragmentViewModel.getGoalsList()

        parentKidGoalsFragmentViewModel.goalsValue.observe(this.viewLifecycleOwner){
            val goalAdapter = KidGoalsFragmentAdapter(it)
            goalsRV.adapter = goalAdapter
            clickItem(goalAdapter)

        }

    }

    private fun clickItem(goalAdapter: KidGoalsFragmentAdapter) {
        goalAdapter.shortOnClickListener = object : KidGoalsFragmentAdapter.ShortOnClickListener{
            override fun ShortClick(goal: Goal) {
                if (!goal.isApproval){
                    val bundle = Bundle()
                    bundle.putString("goal name", goal.goalName)
                    bundle.putInt("goal value", goal.goalValue)
                    findNavController().navigate(R.id.action_kidsGoalsFragment_to_goalApproveFragment, bundle)
                }
            }

        }
    }


}