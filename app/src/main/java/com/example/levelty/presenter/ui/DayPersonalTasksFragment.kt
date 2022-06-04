package com.example.levelty.presenter.ui

import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.levelty.R
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.domain.models.DateTask
import com.example.levelty.presenter.adapters.FragmentDayPersonalTasksAdapter
import com.example.levelty.presenter.adapters.PickerAdapter
import com.example.levelty.presenter.adapters.PickerLayoutManager
import com.example.levelty.presenter.factories.DayPersonalTasksFragmentViewModelFactory
import com.example.levelty.presenter.viewmodels.DayPersonalTasksFragmentViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class DayPersonalTasksFragment : Fragment() {


    @Inject
    lateinit var dayPersonalTasksFragmentViewModelFactory: DayPersonalTasksFragmentViewModelFactory
    val dayPersonalTasksFragmentViewModel: DayPersonalTasksFragmentViewModel by viewModels { dayPersonalTasksFragmentViewModelFactory }

    var dayPersonalTaskRecyclerView: RecyclerView? = null


    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .dayPersonalTasksFragmentInject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dayPersonalTaskRecyclerView =
            view?.findViewById(R.id.rv_fragment_day_personal_tasks_tasks_list)
        return inflater.inflate(R.layout.fragment_day_personal_tasks, container, false )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

     //   val dataPicker: NumberPicker = view.findViewById(R.id.np_fragment_day_personal_tasks_numbers)
        val addNewTaskButton: FloatingActionButton = view.findViewById(R.id.fb_day_personal_tasks_fragment_add)
        val swipeRefresh: SwipeRefreshLayout = view.findViewById(R.id.swipe_fragment_day_personal_task)


        // ***** Fill data Days personal tasks

        dayPersonalTasksFragmentViewModel.getDayTasks()

        dayPersonalTasksFragmentViewModel.dayTaskList.observe(this.viewLifecycleOwner){
            Log.d("MyLog", "recycler task, List = $it")

            val dayPersonalTaskRecyclerView: RecyclerView = view.findViewById(R.id.rv_fragment_day_personal_tasks_tasks_list)

            val fragmentDayPersonalTasksAdapter = FragmentDayPersonalTasksAdapter(it)
            dayPersonalTaskRecyclerView.adapter = fragmentDayPersonalTasksAdapter


        }

        // ***** Swipe items *****
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = false

        }

        setItemTouchHelper()



        // ***** Go to NewTaskFragment *****
        addNewTaskButton.setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_dayPersonalTasksFragment_to_newTaskFragment)
        }

        // ***** Date Experiment ******


        val dateRV: RecyclerView = view.findViewById(R.id.rv_fragment_day_personal_task_date)
//        dateRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        val snapHelper: SnapHelper = LinearSnapHelper()
//        snapHelper.attachToRecyclerView(dateRV)
//        dateRV.adapter = dateTasksFragmentAdapter


        val pickerLayoutManager = PickerLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        pickerLayoutManager.setChangeAlpha(true)
//        pickerLayoutManager.setScaleDownBy(0.99f)
//        pickerLayoutManager.setScaleDownDistance(0.8f)


        val formatDateDay = SimpleDateFormat("dd", Locale.getDefault())
        val formatDateMonth = SimpleDateFormat("MMMM", Locale.getDefault())
        val todayDate = Calendar.getInstance()
        todayDate.add(Calendar.DATE, -365)
        //.timeInMillis

        val dateValues = mutableListOf<DateTask>()
        var counter = 0L
        for (day in 1..742){
            todayDate.add(Calendar.DATE, 1)
            dateValues.add(DateTask(
                counter, formatDateDay.format(todayDate.timeInMillis),
                formatDateMonth.format(todayDate.timeInMillis)))
            counter++
 //           Log.d("MyLog", "day = ${formatDateDay.format(todayDate.timeInMillis)}, month = ${formatDateMonth.format(todayDate.timeInMillis)}")
        }


        val dateTasksFragmentAdapter = context?.let { PickerAdapter(it, dateValues.toList(), dateRV) }
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(dateRV)
        dateRV.setLayoutManager(pickerLayoutManager)
        dateRV.layoutManager?.scrollToPosition(362)
        dateRV.adapter = dateTasksFragmentAdapter


//        adapter = new open fun PickerAdapter())

        pickerLayoutManager.setOnScrollStopListener( object : PickerLayoutManager.scrollStopListener{
            override fun selectedView(view: View?) {
                val day = view?.findViewById<TextView>(R.id.tv_date_item_number)
                val month = view?.findViewById<TextView>(R.id.tv_date_item_month)

                Toast.makeText(
                    context,
                    "Selected date ${day?.text} ${month?.text}", Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    private fun setItemTouchHelper(){
        ItemTouchHelper (object  : ItemTouchHelper.Callback (){

            private val limitScrollX = dipToPx(100f, activity!!.applicationContext)
      //          убрать !!


            private var currentScrollX = 0
            private var currentScrollXWhenInActive = 0
            private var initXWhenInActive = 0F
            private var firstInActive = false

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlag = 0
                val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                return makeMovementFlags(dragFlag, swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                }

            override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
                return Integer.MAX_VALUE.toFloat()
            }

            override fun getSwipeEscapeVelocity(defaultValue: Float): Float {
                return Integer.MAX_VALUE.toFloat()
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    if (dX == 0f){
                        currentScrollX = viewHolder.itemView.scrollX
                        firstInActive = true
                    }
                    if (isCurrentlyActive){
                        var scrollOffset = currentScrollX + (-dX).toInt()
                        if (scrollOffset > limitScrollX){
                            scrollOffset = limitScrollX
                        }else if (scrollOffset < limitScrollX){
                            scrollOffset = 0
                        }
                        viewHolder.itemView.scrollTo(scrollOffset, 0)
                    }
                }else {

                    if (firstInActive){
                        firstInActive = false
                        currentScrollXWhenInActive = viewHolder.itemView.scrollX
                        initXWhenInActive = dX
                    }
                    if (viewHolder.itemView.scrollX < limitScrollX){
                        viewHolder.itemView.scrollTo((currentScrollXWhenInActive * dX / initXWhenInActive).toInt(), 0)
                    }
                }
            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)
                if (viewHolder.itemView.scrollX > limitScrollX){
                    viewHolder.itemView.scrollTo(limitScrollX, 0)
                } else if (viewHolder.itemView.scrollX < 0){
                    viewHolder.itemView.scrollTo(0, 0)
                }
            }

        }).apply {
            attachToRecyclerView(dayPersonalTaskRecyclerView)
        }
    }

    private fun dipToPx(dipValue: Float, context: Context): Int{
        return (dipValue * context.resources.displayMetrics.density).toInt()
    }

}