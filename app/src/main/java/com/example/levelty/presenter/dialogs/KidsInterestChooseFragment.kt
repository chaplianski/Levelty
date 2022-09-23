package com.example.levelty.presenter.dialogs


//class KidsInterestChooseFragment : BottomSheetDialogFragment() {
//
//    @Inject
//    lateinit var kidsInterestChooseFragmentViewModelFactory: KidsInterestChooseFragmentViewModelFactory
//    val kidsInterestChooseFragmentViewModel: KidsInterestChooseFragmentViewModel by viewModels { kidsInterestChooseFragmentViewModelFactory }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        DaggerAppComponent.builder()
//            .context(context)
//            .build()
//            .interestChooseFragmentInject(this)
//    }
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_kids_interest_choose, container, false)
//    }
//
//    override fun getTheme(): Int {
//        return R.style.AppBottomSheetDialogTheme
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val interestRV: RecyclerView = view.findViewById(R.id.rv_fragment_kids_interest_choose)
//        val closeButton: ImageView = view.findViewById(R.id.iv_fragment_kids_interest_choose_close)
//        val saveButton: Button = view.findViewById(R.id.bt_fragment_kids_interest_choose_save)
//
//        kidsInterestChooseFragmentViewModel.getInterestVariants()
//        kidsInterestChooseFragmentViewModel.interestList.observe(this.viewLifecycleOwner){
//            val interestAdapter = OrderStringAdapter(it.map { (id, value) -> value})
//            interestRV.layoutManager = LinearLayoutManager(context)
//            interestRV.adapter = interestAdapter
//
//            interestAdapter.shortOnClickListener = object : OrderStringAdapter.ShortOnClickListener{
//                override fun ShortClick(item: String) {
//                    val navController = findNavController()
//                    navController.previousBackStackEntry?.savedStateHandle?.set("interest", item)
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