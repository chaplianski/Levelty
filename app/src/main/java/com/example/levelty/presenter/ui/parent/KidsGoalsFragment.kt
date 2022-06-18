package com.example.levelty.presenter.ui.parent

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.presenter.adapters.parent.KidGoalsFragmentAdapter
import com.example.levelty.presenter.factories.parent.KidGoalsFragmentViewModelFactory
import com.example.levelty.presenter.viewmodels.parent.KidGoalsFragmentViewModel
import javax.inject.Inject


class KidsGoalsFragment : Fragment() {

    @Inject
    lateinit var kidGoalsFragmentViewModelFactory: KidGoalsFragmentViewModelFactory
    val kidGoalsFragmentViewModel: KidGoalsFragmentViewModel by viewModels { kidGoalsFragmentViewModelFactory }

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

        kidGoalsFragmentViewModel.getGoalsList()

        kidGoalsFragmentViewModel.goalsValue.observe(this.viewLifecycleOwner){
            val goalAdapter = KidGoalsFragmentAdapter(it)
            goalsRV.adapter = goalAdapter

        }

    }


}