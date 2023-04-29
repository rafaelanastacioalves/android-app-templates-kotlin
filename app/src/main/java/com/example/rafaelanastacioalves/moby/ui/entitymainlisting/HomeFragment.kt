package com.example.rafaelanastacioalves.moby.ui.entitymainlisting

import MainScreen
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.rafaelanastacioalves.moby.R
import com.example.rafaelanastacioalves.moby.application.MainApplication
import com.example.rafaelanastacioalves.moby.domain.interactors.MainEntityListInteractor
import com.example.rafaelanastacioalves.moby.ui.theme.ProjectTheme
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_detail_entity_detail_view.composeView

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private val mClickListener = this
    private var mainEntityAdapter: MainEntityAdapter? = null
    private var mRecyclerView: RecyclerView? = null
    private val mMainScreenViewModel: MainScreenViewModel by viewModels<MainScreenViewModel> {
        MainScreenViewModelFactory(MainEntityListInteractor((requireActivity().application as MainApplication).getAppRepository))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        loadData()
        return ComposeView(requireContext()).apply {
            setContent {
                ProjectTheme {
                    MainScreen(mMainScreenViewModel, { id -> findNavController().navigate(id)})
                }
            }
        }
    }

    private fun loadData() {
        mMainScreenViewModel.loadDataIfNecessary()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment()
    }
}
