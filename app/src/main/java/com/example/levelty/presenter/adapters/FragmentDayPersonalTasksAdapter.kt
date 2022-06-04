package com.example.levelty.presenter.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.domain.models.Task
import com.google.android.material.chip.Chip
import java.lang.ref.WeakReference

class FragmentDayPersonalTasksAdapter (dayTasksList: List<Task>) : RecyclerView.Adapter<FragmentDayPersonalTasksAdapter.ViewHolder>(){

    val tasksList = dayTasksList as MutableList<Task>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_day_personal_tasks_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(tasksList[position])

        holder.onDeleteClick = {
            removeItem(it)
        }

    }

    private fun removeItem(viewHolder: RecyclerView.ViewHolder) {
        tasksList.removeAt(viewHolder.position)
        notifyItemRemoved(viewHolder.position)
    }

    override fun getItemCount(): Int {
        return tasksList.size
    }



    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

        val taskName: TextView = itemView.findViewById(R.id.tv_fragment_day_personal_tasks_item_task)
        val taskCoins: TextView = itemView.findViewById(R.id.tv_fragment_day_personal_tasks_item_coins)
        val tasksMoney: TextView = itemView.findViewById(R.id.tv_fragment_day_personal_tasks_item_money)
        val taskCondition: TextView = itemView.findViewById(R.id.tv_fragment_day_personal_tasks_item_condition)
        val taskApprove: Chip = itemView.findViewById(R.id.chip_tv_fragment_day_personal_tasks_item_approve)
        val view = WeakReference(itemView)

   //     lateinit var textViewDelete: TextView

        var onDeleteClick: ((RecyclerView.ViewHolder) -> Unit)? = null

        init {
            view.get()?.let {
                it.setOnClickListener {
                    if (view.get()?.scrollX != 0) {
                        view.get()?.scrollTo(0, 0)
                }
                }

//            textViewDelete.setOnClickListener {
//
//                onDeleteClick.let { onDeleteClick ->
//                    if (onDeleteClick != null) {
//                        onDeleteClick(this)
//                    }
//
//                }
//            }

            }

            fun updateView(){
                view.get()?.scrollTo(0,0)
            //  textView.text = "index$index"
            }

        }

        fun onBind(task: Task){
            taskName.text = task.taskName
            tasksMoney.text = (task.taskPoints*10).toString()
            taskCoins.text = task.taskPoints.toString()
            Log.d("MyLog", "name = ${taskName.text}")
        }

    }



}





//class DateTasksFragmentAdapter :
//    BaseWheelPickerView.Adapter<CustomWheelPickerView.Item, CustomWheelViewHolder>() {
////
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomWheelViewHolder {
//        val view =
//            LayoutInflater.from(parent.context)
//                .inflate(R.layout.custom_wheel_picker_item, parent, false)
//        return CustomWheelViewHolder(view)
//    }
//}
//
//class CustomWheelPickerView @JvmOverloads constructor(
//    context: Context,
//    attrs: AttributeSet? = null,
//    defStyleAttr: Int = 0
//) : BaseWheelPickerView(context, attrs, defStyleAttr) {
//
//    data class Item(val id: String, val icon: Drawable?, val text: String)
//
//    private val highlightView: View = run {
//        val view = View(context)
//        view.background = ContextCompat.getDrawable(context, R.drawable.custom_wheel_highlight_bg)
//        view
//    }
//
//    val adapter: CustomWheelAdapter = DateTasksFragmentAdapter()
//
//    init {
//        setAdapter(adapter)
//        addView(highlightView)
//        (highlightView.layoutParams as? LayoutParams)?.apply {
//            width = ViewGroup.LayoutParams.MATCH_PARENT
//            height =
//                context.resources.getDimensionPixelSize(R.dimen.custom_wheel_picker_item_height)
//            gravity = Gravity.CENTER_VERTICAL
//        }
//    }
//}
//
//class CustomWheelViewHolder(itemView: View) :
//    BaseWheelPickerView.ViewHolder<CustomWheelPickerView.Item>(itemView) {
//    private val textView: TextView = itemView.findViewById(R.id.text_view)
//    private val imageView: ImageView = itemView.findViewById(R.id.icon_image_ivew)
//    override fun onBindData(data: CustomWheelPickerView.Item) {
//        textView.text = data.text
//        imageView.setImageDrawable(data.icon)
//    }
//}
