package com.example.rafaelanastacioalves.moby.ui.entitydetailing


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rafaelanastacioalves.moby.R
import com.example.rafaelanastacioalves.moby.application.MainApplication
import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails
import com.example.rafaelanastacioalves.moby.domain.interactors.EntityDetailsInteractor
import com.google.accompanist.themeadapter.material.MdcTheme
import kotlinx.android.synthetic.main.fragment_detail_entity_detail_view.composeView
import kotlinx.android.synthetic.main.fragment_detail_entity_detail_view.view.*


/**
 * A simple [Fragment] subclass.
 */
class EntityDetailsFragment : Fragment(), View.OnClickListener {
    private val mEntityDetailsViewModel: EntityDetailsViewModel by viewModels<EntityDetailsViewModel> {
        EntityDetailsViewModelFactory(EntityDetailsInteractor((requireActivity().application as MainApplication).getAppRepository))
    }

    val params by navArgs<EntityDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mEntityDetailsViewModel.setEntityId(params.argentityid)
        return ComposeView(requireActivity()).apply {
            setContent {
                MdcTheme {
                    EntityDetailsCompose(mEntityDetailsViewModel, goBack = { findNavController().navigateUp() })
                }
            }
        }
    }

    override fun onClick(v: View) {
        Toast.makeText(activity, "Comprado!", Toast.LENGTH_SHORT).show()
    }

}
