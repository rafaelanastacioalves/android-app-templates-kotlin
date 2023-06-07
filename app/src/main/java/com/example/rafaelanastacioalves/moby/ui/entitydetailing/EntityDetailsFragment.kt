package com.example.rafaelanastacioalves.moby.ui.entitydetailing


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rafaelanastacioalves.moby.R
import com.example.rafaelanastacioalves.moby.binding.ActivityDataBindingComponent
import com.example.rafaelanastacioalves.moby.databinding.FragmentDetailEntityDetailViewBinding
import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


/**
 * A simple [Fragment] subclass.
 */
class EntityDetailsFragment : Fragment(), View.OnClickListener {// Required empty public constructor
    lateinit private var mLiveDataEntityDetailsViewModel: LiveDataEntityDetailsViewModel
    lateinit private var binding : FragmentDetailEntityDetailViewBinding
    private val dataBindingComponent: DataBindingComponent  by lazy { ActivityDataBindingComponent(requireActivity()) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadData()
    }

    private fun loadData() {
        val mEntityId = arguments!!.getString(ARG_ENTITY_ID)
        mLiveDataEntityDetailsViewModel = ViewModelProvider.NewInstanceFactory().create(LiveDataEntityDetailsViewModel::class.java)
        mLiveDataEntityDetailsViewModel.loadData(mEntityId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail_entity_detail_view,
            container,
            false,
            dataBindingComponent
        )
        binding.loadListener = object : Callback {
            override fun onSuccess() {
                activity!!.supportStartPostponedEnterTransition()
                Toast.makeText(requireContext(), "Image Loaded!", Toast.LENGTH_LONG).show()
            }

            override fun onError(e: Exception) {
                activity!!.supportStartPostponedEnterTransition()
                Toast.makeText(requireContext(), "Image NOT Loaded!", Toast.LENGTH_LONG).show()

            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = this
        // o exmeplo removeu este super...
        binding.entityDetail = mLiveDataEntityDetailsViewModel.entityDetails

        super.onViewCreated(view, savedInstanceState)
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


    override fun onClick(v: View) {
        Toast.makeText(activity, "Comprado!", Toast.LENGTH_SHORT).show()
    }

    companion object {
        var ARG_ENTITY_ID: String? = null
    }


}
