package com.example.levelty.presenter.dialogs

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.example.levelty.presenter.adapters.parent.RepeatChooseFragmentAdapter
import com.example.levelty.presenter.factories.parent.RepeatChooseFragmentViewModelFactory
import com.example.levelty.presenter.viewmodels.parent.RepeatChooseFragmentViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject


class RepeatChooseFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var repeatChooseFragmentViewModelFactory: RepeatChooseFragmentViewModelFactory
    val repeatChooseFragmentViewModel: RepeatChooseFragmentViewModel by viewModels { repeatChooseFragmentViewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .repeatChooseFragmentInject(this)
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repeat_choose, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val saveButton: Button = view.findViewById(R.id.bt_fragment_repeat_choose_save)
        val repeatRV: RecyclerView = view.findViewById(R.id.rv_fragment_repeat_choose)
        val closeButton: ImageView = view.findViewById(R.id.iv_fragment_repeat_choose_close)


        repeatChooseFragmentViewModel.getRepeatVariants()
        repeatChooseFragmentViewModel.repeatList.observe(this.viewLifecycleOwner){
            val repeatList: List<String> = it.map { (id, value) -> value }
            val repeatAdapter = RepeatChooseFragmentAdapter(repeatList)
            repeatRV.layoutManager = LinearLayoutManager(context)
            repeatRV.setHasFixedSize(true)
            repeatRV.adapter = repeatAdapter

            repeatAdapter.shortOnClickListener = object :
                RepeatChooseFragmentAdapter.ShortOnClickListener {

                override fun ShortClick(item: String, isLast: Boolean) {
                    val navController = findNavController()
                    if (isLast == true){
                        Log.d("MyLog", "item = $item, isLast = $isLast")
                        navController.navigate(R.id.action_repeatChooseFragment_to_repeatChoiceDialogFragment)
                    }else{
                        Log.d("MyLog", "item = $item, isLast = $isLast")
                        navController.previousBackStackEntry?.savedStateHandle?.set("repeat", item)
                        saveButton.setOnClickListener {
                            dismiss()
                        }
                    }



                }


            }
        }

        closeButton.setOnClickListener {
            dismiss()
        }


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


}