package com.example.levelty.presenter.ui.parent

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.view.WindowManager
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.levelty.R
import com.example.levelty.databinding.FragmentProfileBinding
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.domain.models.Kid
import com.example.levelty.domain.models.Task
import com.example.levelty.presenter.adapters.GoalsProfileFragmentAdapter
import com.example.levelty.presenter.adapters.InterestsProfileFragmentAdapter
import com.example.levelty.presenter.adapters.PieChartCategoryAdapter
import com.example.levelty.presenter.adapters.parent.KidProfileFragmentAdapter
import com.example.levelty.presenter.adapters.parent.PurposeProfileFragmentAdapter
import com.example.levelty.presenter.adapters.parent.UpcomingTasksProfileFragmentAdapter
import com.example.levelty.presenter.factories.parent.ProfileFragmentViewModelFactory
import com.example.levelty.presenter.utils.CURRENT_KID_ID
import com.example.levelty.presenter.viewmodels.parent.ProfileFragmentViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.chip.Chip
import java.text.DateFormatSymbols
import java.util.*
import javax.inject.Inject


class ParentProfileFragment : Fragment() {

    @Inject
    lateinit var profileFragmentViewModelFactory: ProfileFragmentViewModelFactory
    val profileFragmentViewModel: ProfileFragmentViewModel by viewModels { profileFragmentViewModelFactory }


    var _binding: FragmentProfileBinding? = null
    val binding get() = _binding!!

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .profileFragmentInject(this)
        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        requireActivity().window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
            _binding = FragmentProfileBinding.inflate(inflater, container, false)
            return binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val collapsingToolbar =
            view.findViewById(R.id.profile_collapsing_toolbar) as CollapsingToolbarLayout
        val appBar = view.findViewById(R.id.profile_appbar) as AppBarLayout

        appBar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (collapsingToolbar.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(
                    collapsingToolbar
                )
            ) {

                view.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

            } else {
                view.systemUiVisibility = 0
            }
        }

        val taskProgressBar: ProgressBar = binding.pbProfileFragmentProgressImage
        val textProgress: TextView = binding.tvProfileFragmentProgressText
        val dayTaskButton: Chip = binding.chipProfileFragmentDay
        val parentPurposeButton: Chip = binding.chipProfileFragmentPurposes
        val goalsButton: Chip = binding.chipProfileFragmentGoals
        val kidInterestsButton: TextView = binding.tvProfileFragmentInterests
        val bottomNavigation: BottomNavigationView = binding.bvProfileFragmentBottomNavView
        val kidImage: ImageView = binding.ivProfileFragmentKidImage
        val kidNameText: TextView = binding.tvProfileFragmentKidName
        val kidLevelText: TextView = binding.tvProfileFragmentKidLevel

        val tasksPie = binding.pieProfileFragmentTasks
        val categoryPie = binding.pieProfileFragmentCategories

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val currentKidId = sharedPref?.getLong(CURRENT_KID_ID, -1)
        var kidName = ""
        var currentKid = Kid(0, "")

        //****** Current date *****

        val dateItem = getCurrentDate()

        // *******  Download kid data *****

        if (currentKidId != null) {
            if (currentKidId >= 0) currentKidId.let { profileFragmentViewModel.getKid(it) }
        }

        profileFragmentViewModel.kidValue.observe(this.viewLifecycleOwner) { kid ->
            kidName = kid.kidName
            currentKid = kid
            fillKidData(kidImage)
            profileFragmentViewModel.getTodayTasks(kid.kidName, dateItem)
        }


        // ***** Go to
        kidInterestsButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_tasksFragment)
        }

        // **** Add kid list *****

        profileFragmentViewModel.getKids()

        profileFragmentViewModel.kidList.observe(this.viewLifecycleOwner) {
            val kidAdapter = KidProfileFragmentAdapter(it, currentKid)
            val purposeRV = binding.rvProfileFragmentKids
            purposeRV.adapter = kidAdapter

            kidAdapter.kidShortOnClickListener =
                object : KidProfileFragmentAdapter.KidShortOnClickListener {
                    override fun shortClick(kid: Kid) {

                        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                        sharedPref?.edit()?.putLong(CURRENT_KID_ID, kid.kidId)?.apply()

                        kidNameText.text = kid.kidName
                        fillKidData(kidImage)
                        profileFragmentViewModel.getTodayTasks(kid.kidName, dateItem)
                    }

                    override fun shortAddClick() {
                        Log.d("MyLog", "Add new kid")
                    }
                }
        }

        // **** Tasks card *****

        profileFragmentViewModel.getTodayTasks(kidName, dateItem)
        profileFragmentViewModel.uncommingTasksList.observe(this.viewLifecycleOwner) {

            // ***** Upcoming list ******
            val upcomingTaskList = getUncomingTaskList(it)

            // ***** Progress view in Task card *****
            val currentTaskQuantity = it.size
            val currentProgress = currentTaskQuantity - upcomingTaskList.size

            textProgress.text = "$currentProgress of $currentTaskQuantity tasks are completed"
            taskProgressBar.max = currentTaskQuantity
            taskProgressBar.progress = currentProgress


            // **** Add interest list *****
            getInterestList(it)

            // **** Add goals list *****

            profileFragmentViewModel.getTodayGoals()
            profileFragmentViewModel.kidGoalsList.observe(this.viewLifecycleOwner) {
                val goalsProfileFragmentAdapter = GoalsProfileFragmentAdapter(it)
                val goalsRV = binding.rvProfileFragmentGoals
                goalsRV.adapter = goalsProfileFragmentAdapter
            }

            goalsButton.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_kidsGoalsFragment)
            }

            val pieCategoryList = getPieCategoriesEntries(it)
            val pieTaskList = getPieTaskEntries(it)

//            setupPieChartTasks(tasksPie, 60f)
            setupPieChartTasks(tasksPie, 0f)
            loadPieTasks(tasksPie, pieTaskList, view)

            setupPieChartCategories(categoryPie)
            loadPieCategories(categoryPie, pieCategoryList, view)


            // **** Add purpose list *****

            //       profileFragmentViewModel.getParentsPurpose()
            //       profileFragmentViewModel.parantsPurposeList.observe(this.viewLifecycleOwner){
            getPurposeList(it)
            //       }

        }

        dayTaskButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("kid name", kidName)
            bundle.putString("current date", dateItem)
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_profileFragment_to_dayPersonalTasksFragment, bundle)
        }

        parentPurposeButton.setOnClickListener {

        }

        // ***** Add data pies diagrams *****


//        val taskPieEntries = arrayListOf<PieEntry>()//mutableListOf<PieEntry>()
//
//        taskPieEntries.add(PieEntry(5f, "completed"))
//        taskPieEntries.add(PieEntry(2f, "failed"))
//
//        setupPieChartTasks(tasksPie, 60f)
//        loadPieTasks(tasksPie, taskPieEntries, view)
//
//
//
//        val categoryPieEntries = arrayListOf<PieEntry>()//mutableListOf<PieEntry>()
//
//        categoryPieEntries.add(PieEntry(0.2f, "Creativity"))
//        categoryPieEntries.add(PieEntry(0.1f, "Home"))
//        categoryPieEntries.add(PieEntry(0.1f, "Kindness"))
//        categoryPieEntries.add(PieEntry(0.3f, "School"))
//        categoryPieEntries.add(PieEntry(0.2f, "School"))
//        categoryPieEntries.add(PieEntry(0.1f, "Home"))
//        categoryPieEntries.add(PieEntry(0.1f, "Kindness"))
//
//
//
//
//
//
//
//
//
//
//
//        setupPieChartCategories(categoryPie)
// //       loadPieCategories(categoryPie, categoryPieEntries, view)
//
//        loadPieCategories(categoryPie, categoryPieEntries, view)

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.tasks -> {
                    val bundle = Bundle()
                    bundle.putString("kid name", kidName)

                    val navController = view.let { Navigation.findNavController(it) }
                    navController.navigate(R.id.action_profileFragment_to_tasksFragment, bundle)
                    true
                }
                R.id.profile -> {
                    val navController = view.let { Navigation.findNavController(it) }
                    navController.navigate(R.id.action_profileFragment_to_profileChoiceFragment)
                    true
                }
                R.id.settings -> {
//                    val navController = view.let { Navigation.findNavController(it) }
//                    navController.navigate(R.id.action_profileFragment_to_dayKidDetailTasksFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun getUncomingTaskList(it: List<Task>): List<Task> {
        val upcomingTaskList = getUpcomingTasks(it)
        val uncomingTaskAdapter = UpcomingTasksProfileFragmentAdapter(upcomingTaskList)
        val upcomingTaskRV = binding.rvProfileFragmentUncomingTasks
        upcomingTaskRV.adapter = uncomingTaskAdapter
        return upcomingTaskList
    }

    private fun getInterestList(it: List<Task>) {
        val interestList = getInerestList(it)
        val interestsProfileFragmentAdapter = InterestsProfileFragmentAdapter(interestList)
        val interestRV = binding.rvProfileFragmentInterests
        interestRV.adapter = interestsProfileFragmentAdapter
    }

    private fun getPurposeList(it: List<Task>) {
        val purposeList = it.map { task -> task.taskParentPurpose }.toSet().toList()
        val purposeProfileFragmentAdapter = PurposeProfileFragmentAdapter(purposeList)
        val purposeRV = binding.rvProfileFragmentPurposes
        purposeRV.adapter = purposeProfileFragmentAdapter
    }

    private fun fillKidData(kidImage: ImageView) {

        view?.context?.let {
            Glide.with(it).load(R.drawable.kid_icon_1)  // должно быть (kid.image)
                .override(68, 68)
                .centerCrop()
                .circleCrop()
                .into(kidImage)
        }

        // ******** Data get from task *************
//        profileFragmentViewModel.getInterests()
//        profileFragmentViewModel.getParentsPurpose()
//        profileFragmentViewModel.getTodayGoals()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getPieCategoriesEntries(it: List<Task>): MutableList<PieEntry> {
        val pieCategoryList = mutableListOf<PieEntry>()
        val pieCategorySet = mutableSetOf<String>()
        for (task in it) {
            pieCategorySet.add(task.taskCategory)
        }
        for (category in pieCategorySet) {
            var categoryCount = 0f
            for (task in it) {
                if (task.taskCategory == category)
                    categoryCount += 0.1f
            }
            pieCategoryList.add(PieEntry(categoryCount, category))
        }
        return pieCategoryList
    }

    private fun getPieTaskEntries(it: List<Task>): MutableList<PieEntry> {
        val pieTaskList = mutableListOf<PieEntry>()
        val pieTaskSet = mutableSetOf<String>()
        for (task in it) {
            pieTaskSet.add(task.taskStatus)
        }
        for (status in pieTaskSet) {
            var statusCount = 0f
            for (task in it) {
                if (task.taskStatus == status)
                    statusCount += 1f
            }
            pieTaskList.add(PieEntry(statusCount, status))
        }
        return pieTaskList
    }

    private fun getInerestList(tasks: List<Task>): List<String> {
        return tasks.map { task -> task.taskKidsInterest }.toSet().toList()
    }

    private fun getUpcomingTasks(taskList: List<Task>): List<Task> {
        val upcomingTaskList = mutableListOf<Task>()
        for (task in taskList) {
            if (task.taskStatus == "Upcoming") {
                upcomingTaskList.add(task)
            }
        }
        return upcomingTaskList
    }

    private fun setupPieChartCategories(pieChart: PieChart) {
        pieChart.isDrawHoleEnabled = true
        pieChart.setDrawEntryLabels(false)
        pieChart.holeRadius = 75f
        pieChart.setDrawRoundedSlices(true)

        // ограничивает окружность
        pieChart.maxAngle = 360f


        //       Log.d("MyLog", "v = $handler")

//        val description = pieChart.description
//        description.text = "All tasks"
//        description.isEnabled = true
//        description.setPosition(1f, 2f)

        pieChart.setExtraOffsets(0f, 0f, 5f, 0f)

        //       pieChart.transparentCircleRadius = 80f
        //      pieChart.setUsePercentValues(true)
        //       pieChart.setEntryLabelTextSize(12f)
        //       pieChart.setEntryLabelColor(Color.BLACK)
        //       pieChart.centerText = "6"
        pieChart.setCenterTextSize(24f)
        pieChart.description?.isEnabled = false


        val legend = pieChart.legend
//        legend?.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
//        legend?.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
//        legend?.orientation = Legend.LegendOrientation.VERTICAL
//        legend.textSize = 14f
//        legend.isWordWrapEnabled = false
//        legend.form = Legend.LegendForm.CIRCLE
//        legend.formSize = 20F
//        legend?.setDrawInside(false)
        legend?.isEnabled = false
    }

    private fun loadPieCategories(pieChart: PieChart, entries: List<PieEntry>, view: View) {


//        entries.add(PieEntry(0.1f, "not complete"))

        val colors = mutableListOf<Int>()//mutableListOf<Int>()

        //       val colorsSet = listOf<Int>(Color.BLUE, Color.CYAN, Color.GRAY, Color.MAGENTA, Color.RED, Color.YELLOW)
        //        for (color in colorsSet){
//            colors.add(color)
//        }

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
//        data.setValueFormatter(PercentFormatter(pieChart))
//        data.setValueTextSize(12f)
//        data.setValueTextColor(Color.BLACK)
        //       Log.d("MyLog", "dataset: $dataSet")

        pieChart.data = data
        pieChart.invalidate()

        val categoryCountText: TextView =
            view.findViewById(R.id.tv_profile_fragment_category_counrt)
        categoryCountText.text = entries.size.toString()

        val pieChartCategoryAdapter = PieChartCategoryAdapter(entries, colors)
        val pieRV: RecyclerView = view.findViewById(R.id.rv_profile_fragment_pie_category)
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

//        for (color in ColorTemplate.MATERIAL_COLORS){
//            colors.add(color)
//        }

        val dataSet = PieDataSet(entries, "")//"All tasks")
        dataSet.colors = colors

        val data = PieData(dataSet)
        data.setDrawValues(false)

        pieChart.data = data
        pieChart.invalidate()

        val taskCountText: TextView = view.findViewById(R.id.tv_profile_fragment_tasks_counrt)
        var allTasks = 0f
        for (entry in entries) {
            allTasks += entry.value
            //          Log.d("MyLog","value = ${entry.value}")
        }


        taskCountText.text = allTasks.toInt().toString()

        val pieChartCategoryAdapter = PieChartCategoryAdapter(entries, colors)
        val pieRV: RecyclerView = view.findViewById(R.id.rv_profile_fragment_pie_task)
        pieRV.layoutManager = LinearLayoutManager(context)
        pieRV.adapter = pieChartCategoryAdapter
    }

    fun getCurrentDate(): String {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        val currentDayValue = calendar[Calendar.DAY_OF_MONTH]
        val currentYearValue = calendar[Calendar.YEAR]
        val currentMonthValue = DateFormatSymbols().months[calendar.get(Calendar.MONTH)]
        return "$currentMonthValue $currentDayValue $currentYearValue"
    }


}