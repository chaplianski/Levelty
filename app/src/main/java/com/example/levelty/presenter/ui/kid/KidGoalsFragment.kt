package com.example.levelty.presenter.ui.kid

import android.content.Context
import android.os.Bundle
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
import com.example.levelty.presenter.adapters.kid.KidGoalsWheelAdapter
import com.example.levelty.presenter.adapters.kid.TaskPickerLayoutManager
import com.example.levelty.presenter.factories.kid.KidGoalsFragmentViewModelFactory
import com.example.levelty.presenter.factories.parent.ParentKidGoalsFragmentViewModelFactory
import com.example.levelty.presenter.utils.CURRENT_KID_COINS
import com.example.levelty.presenter.utils.CURRENT_KID_LEVEL
import com.example.levelty.presenter.utils.CURRENT_KID_NAME
import com.example.levelty.presenter.utils.getKidBottomNavigationBar
import com.example.levelty.presenter.viewmodels.kid.KidGoalsFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
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
//        val kidName = binding.tvKidGoalsFragmentKidName
//        val kidLevel = binding.tvKidGoalsFragmentKidLevel
//        val kidCoins = binding.ivKidGoalsFragmentCoins

        val bottomNavigation: BottomNavigationView = binding.bottomAppBarKidGoalsFragment
        val goalPickerLayoutManager =
            TaskPickerLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

//        getKidBottomNavigationBar(bottomNavigation, view)
        bottomNavigation.selectedItemId = R.id.kid_goals
        getBottonNavigation(bottomNavigation)
        bottomNavigation.itemIconTintList = null

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

//    private fun getKidBottomNavigationBar(bottomNavigation: BottomNavigationView) {
//        bottomNavigation.setOnItemSelectedListener { itemMenu ->
//            when (itemMenu.itemId) {
//                R.id.tasks -> {
//                    findNavController().navigate(R.id.kidDayTasksFragment)
//                    true
//                }
//                R.id.profile -> {
//                    findNavController().navigate(R.id.kidProfileFragment)
//                    true
//                }
//                R.id.goals -> {
//                    findNavController().navigate(R.id.kidGoalsFragment)
//                    true
//                }
//                else -> false
//            }
//
//        }
//    }


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