package com.example.levelty.presenter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.levelty.R


class ProfileChoiceFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_choice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val parentButton: Button = view.findViewById(R.id.bt_profile_choice_fragment_parent)
        val kidButton: Button = view.findViewById(R.id.bt_profile_choice_fragment_kid)

        parentButton.setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_profileChoiceFragment_to_profileFragment)
        }

        kidButton.setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_profileChoiceFragment_to_kidPersonalFragment)
        }
    }



}