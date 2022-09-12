package com.example.levelty.presenter.ui.kid


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.levelty.R
import com.example.levelty.databinding.FragmentKidPersonalBinding
import com.example.levelty.di.DaggerApp
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.presenter.factories.kid.KidProfileFragmentViewModelFactory
import com.example.levelty.presenter.utils.getKidBottomNavigationBar
import com.example.levelty.presenter.viewmodels.kid.KidProfileFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class KidProfileFragment : Fragment() {

    lateinit var kidProfileFragmentViewModelFactory: KidProfileFragmentViewModelFactory
    val kidProfileFragmentViewModel: KidProfileFragmentViewModel by viewModels { kidProfileFragmentViewModelFactory }

    var _binding: FragmentKidPersonalBinding? = null
    val binding get() = _binding!!

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .kidProfileFragmentInject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKidPersonalBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val todayTasksButton = binding.chipKidProfileFragmentToday
        val coinsValue = binding.tvKidProfileFragmentCoinsValue
        val levelValue = binding.tvKidProfileFragmentLevel
        val coinsTasksValue = binding.tvKidProfileFragmentCoinsTasks
        val progressText = binding.tvKidProfileFragmentTodayProgressText
        val progressBar = binding.pbKidProfileFragmentTodayProgressImage
        val interestsButton = binding.chipKidProfileFragmentInterests
        val interestsRV = binding.rvKidProfileFragmentInterests
        val achievementsButton = binding.chipKidProfileFragmentAchievements
        val achievementsRV = binding.rvKidProfileFragmentAchievents
        val tasksPie = binding.pieKidProfileFragmentTasks
        val tasksPieCount = binding.tvProfileFragmentTasksCount
        val tasksPieRV = binding.rvKidProfileFragmentPieTask
        val categoryPie = binding.pieKidProfileFragmentCategories
        val categoryPieCount = binding.tvKidProfileFragmentCategoryCount
        val categoryPieRV = binding.rvKidProfileFragmentPieCategory



        val bottomNavigation: BottomNavigationView = binding.bottomAppBarKidProfileFragment
        getKidBottomNavigationBar(bottomNavigation, view)




//        val toolbar: androidx.appcompat.widget.Toolbar = view.findViewById(com.example.levelty.R.id.toolbar)
//        val collapsingToolbar = view.findViewById(com.example.levelty.R.id.collapsing_toolbar) as CollapsingToolbarLayout
//        val appBar = view.findViewById(com.example.levelty.R.id.appbar) as AppBarLayout

//        Log.d("MyLog", "collapsingToolbar.height = ${collapsingToolbar.height}")

//        appBar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
//            if (collapsingToolbar.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(
//                    collapsingToolbar
//                )
//            ) {
////                Log.d("MyLog", "white")
////                activity?.window?.decorView?.windowInsetsController?.setSystemBarsAppearance(0, APPEARANCE_LIGHT_STATUS_BARS);
//                view.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
////                activity?.window?.setDecorFitsSystemWindows(false)
//
//
//                toolbar.navigationIcon?.setColorFilter(resources.getColor(com.example.levelty.R.color.white), PorterDuff.Mode.SRC_ATOP)
//            } else {
////                Log.d("MyLog", "black")
//
//
//                view.systemUiVisibility = 0
//
//
//
//
//                toolbar.navigationIcon?.setColorFilter(resources.getColor(com.example.levelty.R.color.black), PorterDuff.Mode.SRC_ATOP)
//            }
//        }


        todayTasksButton.setOnClickListener {
            findNavController().navigate(R.id.action_kidProfileFragment_to_kidDayTasksFragment)
        }

//        val levelText: TextView = view.findViewById(R.id.tv_kid_detail_fragment_level)
//        val levelFrame: FrameLayout = view.findViewById(R.id.fl_level)
//
//
//
//        val myBehavior = MyBehavior()
//        val params = levelAny.layoutParams as CoordinatorLayout.LayoutParams
//        params.behavior = myBehavior
//
//
//        val lp = levelFrame.layoutParams as CoordinatorLayout.LayoutParams
//        lp.behavior = MyBehavior()
//
//        mToolBar.getLayoutParams().topMargin = DisplayUtils.getStatusBarHeight(this)

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


}