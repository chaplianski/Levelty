package com.example.levelty.presenter.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.databinding.FragmentProfileBinding
import com.example.levelty.presenter.adapters.PieChartCategoryAdapter
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.chip.Chip


class ProfileFragment : Fragment() {

     var _binding: FragmentProfileBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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

        val taskProgressBar: ProgressBar = binding.pbProfileFragmentProgressImage
        val textProgress: TextView = binding.tvProfileFragmentProgressText
        val dayTaskButton: Chip = binding.chipProfileFragmentDay

        val tasksPie = binding.pieProfileFragmentTasks
        val categoryPie = binding.pieProfileFragmentCategories


        // ***** - Temp data progress - *****
        val currentProgress = 4
        val currentTaskQuantity = 6


        // ***** Progress view in Task card *****
        textProgress.text = "$currentProgress of $currentTaskQuantity tasks are completed"
        taskProgressBar.max = currentTaskQuantity
        taskProgressBar.progress = currentProgress

        dayTaskButton.setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_profileFragment_to_dayPersonalTasksFragment)
        }


        // ***** Add data pies diagrams *****


        val taskPieEntries = arrayListOf<PieEntry>()//mutableListOf<PieEntry>()


        taskPieEntries.add(PieEntry(5f, "completed"))
        taskPieEntries.add(PieEntry(2f, "failed"))

        setupPieChartTasks(tasksPie, 60f)
        loadPieTasks(tasksPie, taskPieEntries, view)

        val categoryPieEntries = arrayListOf<PieEntry>()//mutableListOf<PieEntry>()

        categoryPieEntries.add(PieEntry(0.2f, "Creativity"))
        categoryPieEntries.add(PieEntry(0.1f, "Home"))
        categoryPieEntries.add(PieEntry(0.1f, "Kindness"))
        categoryPieEntries.add(PieEntry(0.3f, "School"))
//        categoryPieEntries.add(PieEntry(0.1f, "Home"))
//        categoryPieEntries.add(PieEntry(0.1f, "Kindness"))
        categoryPieEntries.add(PieEntry(0.2f, "School"))
        categoryPieEntries.add(PieEntry(0.1f, "Home"))
        categoryPieEntries.add(PieEntry(0.1f, "Kindness"))
//        categoryPieEntries.add(PieEntry(1f, "Home"))
//        categoryPieEntries.add(PieEntry(1f, "Kindness"))
//        categoryPieEntries.add(PieEntry(2f, "School"))

        setupPieChartCategories(categoryPie)
        loadPieCategories(categoryPie, categoryPieEntries, view)

    }

    private fun setupPieChartCategories(pieChart: PieChart){
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

    private fun loadPieCategories(pieChart: PieChart, entries: List<PieEntry>, view: View){


//        entries.add(PieEntry(0.1f, "not complete"))

        val colors = mutableListOf<Int>()//mutableListOf<Int>()

 //       val colorsSet = listOf<Int>(Color.BLUE, Color.CYAN, Color.GRAY, Color.MAGENTA, Color.RED, Color.YELLOW)
        //        for (color in colorsSet){
//            colors.add(color)
//        }

        for (color in ColorTemplate.MATERIAL_COLORS){
            colors.add(color)
        }
        for (color in ColorTemplate.VORDIPLOM_COLORS){
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
        Log.d("MyLog", "dataset: $dataSet")

        pieChart.data = data
        pieChart.invalidate()

        val categoryCountText: TextView = view.findViewById(R.id.tv_profile_fragment_category_counrt)
        categoryCountText.text = entries.size.toString()

        val pieChartCategoryAdapter = PieChartCategoryAdapter(entries, colors)
        val pieRV: RecyclerView = view.findViewById(R.id.rv_profile_fragment_pie_category)
        pieRV.layoutManager  = LinearLayoutManager(context)
        pieRV.adapter = pieChartCategoryAdapter



    }

    private fun setupPieChartTasks(pieChart: PieChart, noComplite: Float){
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

    private fun loadPieTasks(pieChart: PieChart, entries: List<PieEntry>, view: View){

        val colors = mutableListOf<Int>()
        for (color in ColorTemplate.VORDIPLOM_COLORS){
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
        for (entry in entries){
            allTasks += entry.value
            Log.d("MyLog","value = ${entry.value}")
        }


        taskCountText.text = allTasks.toInt().toString()

        val pieChartCategoryAdapter = PieChartCategoryAdapter(entries, colors)
        val pieRV: RecyclerView = view.findViewById(R.id.rv_profile_fragment_pie_task)
        pieRV.layoutManager  = LinearLayoutManager(context)
        pieRV.adapter = pieChartCategoryAdapter



    }


}