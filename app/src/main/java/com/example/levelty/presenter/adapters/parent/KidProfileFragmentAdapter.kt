package com.example.levelty.presenter.adapters.parent

import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.levelty.R
import com.example.levelty.domain.models.ChildrenItem
import com.example.levelty.domain.models.Kid
import de.hdodenhof.circleimageview.CircleImageView


class KidProfileFragmentAdapter(private val kidList: List<ChildrenItem>, currentKid: ChildrenItem) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var selectedPosition = getSelectedPosition(currentKid)

    private fun getSelectedPosition(checkedKid: ChildrenItem): Int {
        var position = 0
        for (kid in kidList){
            if (kid == checkedKid) return position
            else position++
        }
        return position
    }

    interface KidShortOnClickListener {
        fun shortClick(childrenItem: ChildrenItem)
        fun shortAddClick()
    }

    var kidShortOnClickListener: KidShortOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            KID -> {
                val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_profile_kid_item, parent, false)
                KidViewHolder(v)
            }
            ADD ->{
                val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_profile_kid_item_add, parent, false)
                AddKidViewHolder(v)
            }
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            KID -> {
                (holder as KidViewHolder).onBind(kidList[position])

                if (selectedPosition == position){
                    holder.kidImage.borderWidth = 4
                    holder.kidImage.borderColor = Color.WHITE
                    holder.kidImage.alpha = 1f
                    holder.kidName.setTextColor(Color.WHITE)
                    holder.kidName.alpha = 1f
                    holder.kidName.isTextSelectable

                }

                holder.itemView.setOnClickListener {
                    if (selectedPosition == position){
                        selectedPosition = -1
                        notifyDataSetChanged()
                        return@setOnClickListener
                    }
                    selectedPosition = position
                    notifyDataSetChanged()
                    kidShortOnClickListener?.shortClick(kidList[position])
                }


            }
            ADD -> {
                (holder as AddKidViewHolder).onBind()
                holder.itemView.setOnClickListener {
                    kidShortOnClickListener?.shortAddClick()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return kidList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return when(position){
            kidList.size -> ADD
            else -> KID
        }
    }

    inner class KidViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val kidImage: CircleImageView = itemView.findViewById(R.id.iv_kid_item_image)
        val kidName: TextView = itemView.findViewById(R.id.tv_kid_item_name)
//        val kidLayout: ConstraintLayout = itemView.findViewById(R.id.cl_kid_item)

        fun onBind(childrenItem: ChildrenItem){

            kidName.text = childrenItem.user?.firstName
            kidName.alpha = 0.6f

            val bitmap = BitmapFactory.decodeResource(itemView.context.resources, R.drawable.kid_icon_2)
            kidImage.setImageBitmap(bitmap)
            kidImage.borderWidth = 0
            kidImage.alpha = 0.9f

//            Glide.with(itemView.context).load(R.drawable.kid_icon_1)
//                .override(68, 68)
//                .centerCrop()
//                .circleCrop()
//                .into(kidImage)
        }

    }

    inner class AddKidViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val kidImageAdd: ImageView = itemView.findViewById(R.id.iv_kid_item_add)
        val kidNameAdd: TextView = itemView.findViewById(R.id.tv_kid_item_name_add)


        fun onBind(){

            kidNameAdd.text = "Add new"
            kidNameAdd.alpha = 0.6f
//            kidImageAdd.alpha = 0.6f

            Glide.with(itemView.context).load(R.drawable.ic_add_new_kid)
                .override(68, 68)
                .centerCrop()
                .circleCrop()
                .into(kidImageAdd)
        }

    }
    companion object {
        private val KID = 0
        private val ADD = 1
    }

}