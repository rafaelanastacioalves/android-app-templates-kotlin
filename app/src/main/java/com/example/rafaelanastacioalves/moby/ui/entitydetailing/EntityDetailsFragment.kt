package com.example.rafaelanastacioalves.moby.ui.entitydetailing


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.rafaelanastacioalves.moby.R
import com.example.rafaelanastacioalves.moby.databinding.FragmentDetailEntityDetailViewBinding
import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 */
class EntityDetailsFragment : Fragment(), View.OnClickListener {// Required empty public constructor
    private val mLiveDataEntityDetailsViewModel: EntityDetailsViewModel by lazy {
        ViewModelProvider(requireActivity()).get(EntityDetailsViewModel::class.java)
    }
    lateinit private var binding : FragmentDetailEntityDetailViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadData()
    }


    /**
     * Here we call a State Flow from viewModel in a secure manner so
     * we are sure we're not wasting resources when activity is destroyed, etc
     * As explained in [this link](https://medium.com/androiddevelopers/migrating-from-livedata-to-kotlins-flow-379292f419fb)
     */
    private fun loadData() {
        val mEntityId = arguments!!.getString(ARG_ENTITY_ID)


        mLiveDataEntityDetailsViewModel.setEntityId(mEntityId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentDetailEntityDetailViewBinding.inflate(
            inflater,
            container,
            false
        )
        observe()
        return binding.root
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                mLiveDataEntityDetailsViewModel.entityDetails.collect{
                        entityDetails -> setViewsWith(entityDetails?.data)
                }
            }
        }
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

    /**
     * The migration to view binding, requires init in onViewCreated, and from now on,
     * you will use "binding" object to get access to its view components, in the camelCase pattern.
     * The views declared in XML can use other patterns.
     */
    private fun setViewsWith(entityDetails: EntityDetails?) {
        binding.apply {
            detailEntityDetailName!!.text = entityDetails?.price
            setupActionBarWithTitle(entityDetails?.title ?: "")
            Picasso.get()
                .load(entityDetails?.imageUrl)
                .into(entityDetailImageview, object : Callback {
                    override fun onSuccess() {
                        activity!!.supportStartPostponedEnterTransition()
                    }
                    override fun onError(e: Exception) {
                    }
                })

        }


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
