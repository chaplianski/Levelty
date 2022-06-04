package com.example.levelty.presenter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.levelty.R
import com.example.levelty.domain.models.Kid

class KidProfileFragmentAdapter(val kidList: List<Kid>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            R.id.iv_kid_item -> {
                val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_profile_kid_item, parent, false)
                KidViewHolder(v)
            }
            R.id.iv_kid_item_add ->{
                val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_profile_kid_item_add, parent, false)
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

        fun onBind(kid: Kid){
            Glide.with(itemView.context).load(kid.kidImage)
                .override(68, 68)
                .centerCrop()
                .circleCrop()
                .into(kidImage)
        }

    }

    inner class AddKidViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val kidAddImage: ImageView = itemView.findViewById(R.id.iv_kid_item_add)

        fun onBind(){
            Glide.with(itemView.context).load(R.drawable.ic_ellipse_29__add_)
                .override(68, 68)
                .centerCrop()
                .circleCrop()
                .into(kidAddImage)
        }

    }


}