package com.example.levelty.presenter.ui.kid


import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.databinding.FragmentKidProfileBinding
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.domain.models.ChildInterestsItem
import com.example.levelty.domain.models.KidProcessedTask
import com.example.levelty.presenter.adapters.PieChartCategoryAdapter
import com.example.levelty.presenter.adapters.parent.SampleStringChipsAdapter
import com.example.levelty.presenter.factories.kid.KidProfileFragmentViewModelFactory
import com.example.levelty.presenter.utils.*
import com.example.levelty.presenter.viewmodels.kid.KidProfileFragmentViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject


class KidProfileFragment : Fragment() {

    @Inject
    lateinit var kidProfileFragmentViewModelFactory: KidProfileFragmentViewModelFactory
    val kidProfileFragmentViewModel: KidProfileFragmentViewModel by viewModels { kidProfileFragmentViewModelFactory }

    var _binding: FragmentKidProfileBinding? = null
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

        requireActivity().window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
            _binding = FragmentKidProfileBinding.inflate(layoutInflater, container, false)
            return binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val kidName = binding.tvKidProfileFragmentTitle
        val coinsValue = binding.tvKidProfileFragmentCoinsValue
        val levelValue = binding.tvKidProfileFragmentLevel
        val coinsTasksValue = binding.tvKidProfileFragmentCoinsTasks
        val todayTasksButton = binding.chipKidProfileFragmentToday
        val progressText = binding.tvKidProfileFragmentTodayProgressText
        val progressBar = binding.pbKidProfileFragmentTodayProgressImage
        val interestsButton = binding.chipKidProfileFragmentInterests
        val interestsRV = binding.rvKidProfileFragmentInterests
        val achievementsButton = binding.chipKidProfileFragmentAchievements
        val achievementsRV = binding.rvKidProfileFragmentAchievents
        val tasksPie = binding.pieKidProfileFragmentTasks
//        val tasksPieCount = binding.tvProfileFragmentTasksCount
//        val tasksPieRV = binding.rvKidProfileFragmentPieTask
        val categoryPie = binding.pieKidProfileFragmentCategories
//        val categoryPieCount = binding.tvKidProfileFragmentCategoryCount
//        val categoryPieRV = binding.rvKidProfileFragmentPieCategory


        val bottomNavigation: BottomNavigationView = binding.bottomAppBarKidProfileFragment
//        getKidBottomNavigationBar(bottomNavigation, view)
        bottomNavigation.selectedItemId = R.id.kid_profile

        getBottonNavigation(bottomNavigation)
        bottomNavigation.itemIconTintList = null

        kidProfileFragmentViewModel.getChild()

        kidProfileFragmentViewModel.child.observe(this.viewLifecycleOwner) { child ->

            kidName.text = child.user?.firstName
            levelValue.text = "LEVEL ${child.level}"
            coinsValue.text = child.balance?.amount.toString()
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            sharedPref?.edit()?.apply{
                putString(CURRENT_KID_NAME, child.user?.firstName)
                child.level?.let { putInt(CURRENT_KID_LEVEL, it) }
                child.balance?.amount?.let { putInt(CURRENT_KID_COINS, it) }
            }?.apply()

            coinsTasksValue.text =
                "${child.assignedTasks?.size} tasks to next level"  // TODO уточнить откуда получать значение и установить правильное

            kidProfileFragmentViewModel.getTasksList(child.assignedTasks)

        }

        kidProfileFragmentViewModel.tasks.observe(this.viewLifecycleOwner) { tasksList ->

//            Log.d("MyLog", "taskList = $tasksList")

            val todayTasks = getTodayTasks(tasksList)
            val unexecutedTasks = getUnexecutedTasks(todayTasks)
            progressText.text = "${unexecutedTasks.size} of ${todayTasks.size} tasks are completed"
            progressBar.max = todayTasks.size
            progressBar.progress = unexecutedTasks.size

            val interestsAdapter = SampleStringChipsAdapter(getInterestsList(tasksList), -1)
            interestsRV.adapter = interestsAdapter

            val pieCategoryList = getPieCategoriesEntries(tasksList)
            val pieTaskList = getPieTaskEntries(tasksList)
//            Log.d("MyLog", "piecategories = $pieCategoryList")
//            Log.d("MyLog", "pietasks = $pieTaskList")

            setupPieChartTasks(tasksPie, 0f)
            loadPieTasks(tasksPie, pieTaskList, view)

            setupPieChartCategories(categoryPie)
            loadPieCategories(categoryPie, pieCategoryList, view)
        }



        todayTasksButton.setOnClickListener {
            findNavController().navigate(R.id.action_kidProfileFragment_to_kidDayTasksFragment)
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

    private fun getUnexecutedTasks(taskList: List<KidProcessedTask>): List<KidProcessedTask> {

        val unexecutedTaskList = mutableListOf<KidProcessedTask>()
        for (task in taskList) {
            if (task.status != DONE_TASK_STATUS) {
                unexecutedTaskList.add(task)
            }
        }
        return unexecutedTaskList
    }

    private fun getTodayTasks(allTasks: List<KidProcessedTask>): List<KidProcessedTask> {
        val todayTasks = mutableListOf<KidProcessedTask>()
        for (task in allTasks){
            if (task.choreDate == getTodayDate()) {
                todayTasks.add(task)
            }
        }
        return todayTasks
    }

    private fun getInterestsList(allTasks: List<KidProcessedTask>): List<String> {
        val interestsList = mutableListOf<ChildInterestsItem?>()
        for (task in allTasks){
            task.childInterests?.let { interestsList.addAll(it) }
        }
        val interestListString = mutableListOf<String>()
        for (interest in interestsList){
            if (interest?.title != null){
                interestListString.add(interest.title)
            }
        }
        return interestListString.toSet().toList()
    }

    private fun getPieCategoriesEntries(tasks: List<KidProcessedTask>): MutableList<PieEntry> {
        val pieCategoryList = mutableListOf<PieEntry>()
        val pieCategorySet = mutableSetOf<String>()
        for (task in tasks) {
            task.category?.title?.let { it1 -> pieCategorySet.add(it1) }
        }
        for (category in pieCategorySet) {
            var categoryCount = 0f
            for (task in tasks) {
                if (task.category?.title == category)
                    categoryCount += 0.1f
            }
            pieCategoryList.add(PieEntry(categoryCount, category))
        }
        return pieCategoryList
    }

    private fun getPieTaskEntries(tasks: List<KidProcessedTask>): MutableList<PieEntry> {
        val pieTaskList = mutableListOf<PieEntry>()
        val pieTaskSet = mutableSetOf<String>()
        for (task in tasks) {
            task.choreStatus?.let { it1 -> pieTaskSet.add(it1) }
        }
        for (status in pieTaskSet) {
            var statusCount = 0f
            for (task in tasks) {
                if (task.choreStatus == status)
                    statusCount += 1f
            }
            pieTaskList.add(PieEntry(statusCount, status))
        }
        return pieTaskList
    }

    private fun setupPieChartCategories(pieChart: PieChart) {
        pieChart.isDrawHoleEnabled = true
        pieChart.setDrawEntryLabels(false)
        pieChart.holeRadius = 75f
        pieChart.setDrawRoundedSlices(true)

        pieChart.maxAngle = 360f
//        Log.d("MyLog", "v = $handler")

        pieChart.setExtraOffsets(0f, 0f, 5f, 0f)

        pieChart.setCenterTextSize(24f)
        pieChart.description?.isEnabled = false


        val legend = pieChart.legend

        legend?.isEnabled = false
    }

    private fun loadPieCategories(pieChart: PieChart, entries: List<PieEntry>, view: View) {

        val colors = mutableListOf<Int>()//mutableListOf<Int>()

        for (color in ColorTemplate.MATERIAL_COLORS) {
            colors.add(color)
        }
        for (color in ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color)
        }

//        Log.d("MyLog", "colors: $colors")
        val dataSet = PieDataSet(entries, "")//"All tasks")
        dataSet.colors = colors

        val data = PieData(dataSet)
        data.setDrawValues(false)
        pieChart.data = data
        pieChart.invalidate()

        val categoryCountText: TextView =
            view.findViewById(R.id.tv_kid_profile_fragment_category_count)
        categoryCountText.text = entries.size.toString()

        val pieChartCategoryAdapter = PieChartCategoryAdapter(entries, colors)
        val pieRV: RecyclerView = view.findViewById(R.id.rv_kid_profile_fragment_pie_category)
        pieRV.layoutManager = LinearLayoutManager(context)
        pieRV.adapter = pieChartCategoryAdapter


    }

    private fun setupPieChartTasks(pieChart: PieChart, noComplite: Float) {
        pieChart.isDrawHoleEnabled = true
        pieChart.setDrawEntryLabels(false)
        pieChart.holeRadius = 75f
        pieChart.setDrawRoundedSlices(true)


        // ограничивает окружность
        pieChart.maxAngle = 360f - noComplite

        pieChart.setExtraOffsets(0f, 0f, 5f, 0f)

        pieChart.setCenterTextSize(24f)
        pieChart.description?.isEnabled = false


        val legend = pieChart.legend
        legend?.isEnabled = false
    }

    private fun loadPieTasks(pieChart: PieChart, entries: List<PieEntry>, view: View) {

        val colors = mutableListOf<Int>()
        for (color in ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color)
        }

        val dataSet = PieDataSet(entries, "")//"All tasks")
        dataSet.colors = colors

        val data = PieData(dataSet)
        data.setDrawValues(false)

        pieChart.data = data
        pieChart.invalidate()

        val taskCountText: TextView = view.findViewById(R.id.tv_kid_profile_fragment_tasks_count)
        var allTasks = 0f
        for (entry in entries) {
            allTasks += entry.value
            //          Log.d("MyLog","value = ${entry.value}")
        }


        taskCountText.text = allTasks.toInt().toString()

        val pieChartCategoryAdapter = PieChartCategoryAdapter(entries, colors)
        val pieRV: RecyclerView = view.findViewById(R.id.rv_kid_profile_fragment_pie_task)
        pieRV.layoutManager = LinearLayoutManager(context)
        pieRV.adapter = pieChartCategoryAdapter
    }
}

