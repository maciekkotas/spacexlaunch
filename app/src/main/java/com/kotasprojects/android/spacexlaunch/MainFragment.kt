package com.kotasprojects.android.spacexlaunch

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.kotasprojects.android.spacexlaunch.adapters.RecyclerViewAdapter
import com.kotasprojects.android.spacexlaunch.databinding.FragmentMainBinding
import com.kotasprojects.android.spacexlaunch.network.SpaceXApiFilter

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentMainBinding.inflate(inflater)

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        binding.recyclerView.adapter =
            RecyclerViewAdapter(RecyclerViewAdapter.OnClickListener {
                viewModel.displayPropertyDetails(it)
            })
        viewModel.navigateToSelectedProperty.observe(this, Observer {
            if (null != it) {
                this.findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateFilter(
            when (item.itemId) {
                R.id.menu_past -> SpaceXApiFilter.SHOW_PAST
                R.id.menu_upcoming -> SpaceXApiFilter.SHOW_UPCOMING
                else -> SpaceXApiFilter.SHOW_ALL
            }
        )
        return true
    }
}

