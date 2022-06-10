package com.example.levelty.presenter.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.presenter.adapters.OrderStringAdapter
import com.example.levelty.presenter.factories.CategoryChooseViewModelFactory
import com.example.levelty.presenter.viewmodels.CategoryChooseViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject


class CategoryChooseFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var categoryChooseViewModelFactory: CategoryChooseViewModelFactory
    val categoryChooseViewModel: CategoryChooseViewModel by viewModels { categoryChooseViewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .categoryChooseFragmentInject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_category_choose, container, false)
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    override fun onStart() {
        super.onStart()

        dialog.let {

            val bottomSheet = it?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val behavior = BottomSheetBehavior.from(bottomSheet)

            behavior.state = BottomSheetBehavior.STATE_EXPANDED
      //      behavior.isHideable = false
            behavior.isDraggable = false
        //    behavior.state = BottomSheetBehavior.SAVE_HIDEABLE
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryRV: RecyclerView = view.findViewById(R.id.rv_fragment_category_choose)
        val saveButton: Button = view.findViewById(R.id.bt_fragment_category_choose_save)

        categoryChooseViewModel.getCategoriesList()

        categoryChooseViewModel.categoryList.observe(this.viewLifecycleOwner){

            val categoryAdapter = OrderStringAdapter(it)
       //     categoryRV.setHasFixedSize(true)
            categoryRV.layoutManager = LinearLayoutManager(context)
            categoryRV.adapter = categoryAdapter
        }

    }
}