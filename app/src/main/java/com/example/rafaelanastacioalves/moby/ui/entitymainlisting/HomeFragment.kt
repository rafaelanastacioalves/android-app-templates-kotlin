package com.example.rafaelanastacioalves.moby.ui.entitymainlisting

import MainScreen
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rafaelanastacioalves.moby.application.MainApplication
import com.example.rafaelanastacioalves.moby.domain.interactors.MainEntityListInteractor
import com.example.rafaelanastacioalves.moby.ui.theme.ProjectTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.findNavController


class HomeFragment : Fragment() {

    private val mMainScreenViewModel: MainScreenViewModelInterface by viewModels<MainScreenViewModel> {
        MainScreenViewModelFactory(MainEntityListInteractor((requireActivity().application as MainApplication).getAppRepository))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                SetHomeFragment(mMainScreenViewModel) { id -> goToDetail(id) }
            }
        }
    }

    private fun goToDetail(id: String) {
        val direction = HomeFragmentDirections.actionHomeFragmentToEntityDetailsFragment(id)
        findNavController().navigate(direction)
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

@Composable
fun SetHomeFragment(
    mainScreenViewModel: MainScreenViewModelInterface,
    onNavigate: (String) -> Unit,
) {
    ProjectTheme {
        MainScreen(
            mainScreenViewModel,
            onNavigate
        )
    }
}
