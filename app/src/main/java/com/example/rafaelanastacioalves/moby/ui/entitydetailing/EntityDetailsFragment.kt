package com.example.rafaelanastacioalves.moby.ui.entitydetailing


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rafaelanastacioalves.moby.R
import com.example.rafaelanastacioalves.moby.application.MainApplication
import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails
import com.example.rafaelanastacioalves.moby.domain.interactors.EntityDetailsInteractor
import com.google.accompanist.themeadapter.material.MdcTheme
import kotlinx.android.synthetic.main.fragment_detail_entity_detail_view.view.*


/**
 * A simple [Fragment] subclass.
 */
class EntityDetailsFragment : Fragment(), View.OnClickListener {
    private val mEntityDetailsViewModel : EntityDetailsViewModel by viewModels<EntityDetailsViewModel> {
        EntityDetailsViewModelFactory(EntityDetailsInteractor((requireActivity().application as MainApplication).getAppRepository))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadData()
    }

    private fun loadData() {
        val mEntityId = requireArguments().getString(ARG_ENTITY_ID)
        mEntityDetailsViewModel.loadData(mEntityId).observe(this, Observer { entityDetails -> setViewsWith(entityDetails?.data) })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val views = inflateViews(inflater, container)
        views.composeView.setContent {
            MdcTheme {
                EntityDetailsCompose(mEntityDetailsViewModel)
            }
        }
        return views
    }


    private fun inflateViews(inflater: LayoutInflater, container: ViewGroup?): View {
        val rootView = inflater.inflate(R.layout.fragment_detail_entity_detail_view, container, false)
        return rootView
    }


    private fun setupActionBarWithTitle(title: String) {
        val mActivity = activity as AppCompatActivity?
        val actionBar = mActivity!!.supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = title


        }
    }

    private fun setViewsWith(entityDetails: EntityDetails?) {

//        detail_entity_detail_name!!.text = entityDetails?.price
//        setupActionBarWithTitle(entityDetails?.title ?: "")
//        val placeHolder: StateListDrawable = requireContext().resources.getDrawable(R.drawable.ic_placeholder_map_selector) as StateListDrawable;
//
//        Picasso.get()
//                .load(entityDetails?.imageUrl)
//                .placeholder(placeHolder)
//                .into(entity_detail_imageview, object : Callback {
//                    override fun onSuccess() {
//                        activity!!.supportStartPostponedEnterTransition()
//                    }
//
//                    override fun onError(e: Exception) {
//
//                    }
//                })


    }


    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onClick(v: View) {
        Toast.makeText(activity, "Comprado!", Toast.LENGTH_SHORT).show()
    }

    companion object {
        var ARG_ENTITY_ID: String? = null
    }


}// Required empty public constructor
