package com.example.levelty.presenter.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.presenter.adapters.parent.OrderStringAdapter
import com.example.levelty.presenter.factories.parent.ParentPurposeChooseViewModelFactory
import com.example.levelty.presenter.viewmodels.parent.ParentPurposeChooseViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject


//class ParentsPurposeChooseFragment : BottomSheetDialogFragment() {
//
//    @Inject
//    lateinit var parentPurposeChooseViewModelFactory: ParentPurposeChooseViewModelFactory
//    val parentPurposeChooseViewModel: ParentPurposeChooseViewModel by viewModels { parentPurposeChooseViewModelFactory }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        DaggerAppComponent.builder()
//            .context(context)
//            .build()
//            .parentPurposeChooseFragmentInject(this)
//    }
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_parents_purpose_choose, container, false)
//    }
//
//    override fun getTheme(): Int {
//        return R.style.AppBottomSheetDialogTheme
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val purposeRV: RecyclerView = view.findViewById(R.id.rv_fragment_parent_purpose_choose)
//        val closeButton: ImageView = view.findViewById(R.id.iv_fragment_parents_purpose_choose_close)
//        val saveButton: Button = view.findViewById(R.id.bt_fragment_parents_purpose_choose_save)
//
//        parentPurposeChooseViewModel.getPurposeVariants()
//        parentPurposeChooseViewModel.purposeList.observe(this.viewLifecycleOwner){ purpose ->
//            val purposeAdapter = OrderStringAdapter(purpose)
//            purposeRV.layoutManager = LinearLayoutManager(context)
//            purposeRV.adapter = purposeAdapter
//
//            purposeAdapter.shortOnClickListener = object : OrderStringAdapter.ShortOnClickListener{
//                override fun ShortClick(item: String) {
//                    val navController = findNavController()
//                    navController.previousBackStackEntry?.savedStateHandle?.set("purpose", item)
//
//                    saveButton.setOnClickListener {
//                        dismiss()
//                    }
//                }
//            }
//        }
//
//        closeButton.setOnClickListener {
//            dismiss()
//        }
//
//    }
//
//    override fun onStart() {
//        super.onStart()
//
//        dialog.let {
//
//            val bottomSheet = it?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
//            val behavior = BottomSheetBehavior.from(bottomSheet)
//
//            behavior.state = BottomSheetBehavior.STATE_EXPANDED
//            //      behavior.isHideable = false
//            behavior.isDraggable = false
//            //    behavior.state = BottomSheetBehavior.SAVE_HIDEABLE
//        }
//    }
//
//}