package com.example.levelty.presenter.adapters.parent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.levelty.R
import com.example.levelty.domain.models.Kid

class KidProfileFragmentAdapter(private val kidList: List<Kid>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


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
            R.id.iv_kid_item -> (holder as KidViewHolder).onBind(kidList[position])
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

        val kidImage: ImageView = itemView.findViewById(R.id.iv_kid_item)
        val kidName: TextView = itemView.findViewById(R.id.tv_kid_item_name)

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