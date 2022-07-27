package com.example.levelty.presenter.adapters.parent

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.levelty.R
import com.example.levelty.domain.models.Kid
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily


class KidProfileFragmentAdapter(private val kidList: List<Kid>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

 //   val sharedPref = context.getSharedPreferences("Kid current position", Context.MODE_PRIVATE)
    var checkedPosition = 0 // sharedPref.getInt("kid position", 0)

    interface KidShortOnClickListener {
        fun shortClick(kid: Kid)
    }

    var kidShortOnClickListener: KidShortOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            R.id.iv_kid_item -> {
                val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_profile_kid_item, parent, false)
                KidViewHolder(v)
            }
            R.id.iv_kid_item_add ->{
                val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_profile_kid_item, parent, false)
                AddKidViewHolder(v)
            }
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            R.id.iv_kid_item -> {
                (holder as KidViewHolder).onBind(kidList[position])

                holder.checkField.isChecked = (checkedPosition == position)

                holder.itemView.setOnClickListener {
                    kidShortOnClickListener?.shortClick (kidList[position])
//                    holder.kidImage.strokeWidth = 25f
//                    holder.kidImage.strokeColor?.defaultColor
                    this.checkedPosition = holder.absoluteAdapterPosition
                    notifyDataSetChanged()
                }
                holder.checkField.setOnClickListener {
                    kidShortOnClickListener?.shortClick (kidList[position])
                    this.checkedPosition = holder.absoluteAdapterPosition
                    notifyDataSetChanged()
                }



            }
            R.id.iv_kid_item_add -> (holder as AddKidViewHolder).onBind()
        }
    }

    override fun getItemCount(): Int {
        return kidList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return when(position){
            kidList.size -> R.id.iv_kid_item_add
            else -> R.id.iv_kid_item
        }
    }

    inner class KidViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val kidImage: ShapeableImageView = itemView.findViewById(R.id.iv_kid_item)
        val kidName: TextView = itemView.findViewById(R.id.tv_kid_item_name)
        val checkField: CheckBox = itemView.findViewById(R.id.cb_kid_item)


        fun onBind(kid: Kid){

            kidName.text = kid.kidName
            Glide.with(itemView.context).load(R.drawable.kid_icon_1)
                .override(68, 68)
                .centerCrop()
                .circleCrop()
                .into(kidImage)
        }

    }

    inner class AddKidViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val kidAddImage: ImageView = itemView.findViewById(R.id.iv_kid_item)
        val kidName: TextView = itemView.findViewById(R.id.tv_kid_item_name)


        fun onBind(){

            kidName.text = "Add new"
            Glide.with(itemView.context).load(R.drawable.ic_add_new_kid)
                .override(68, 68)
                .centerCrop()
                .circleCrop()
                .into(kidAddImage)
        }

    }


}