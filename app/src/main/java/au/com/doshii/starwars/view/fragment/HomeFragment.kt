package au.com.doshii.starwars.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import au.com.doshii.starwars.R
import au.com.doshii.starwars.model.DataOption
import au.com.doshii.starwars.view.adapter.HomeListAdapter
import au.com.doshii.starwars.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * Home Fragment
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val dataOptionListAdapter = HomeListAdapter(arrayListOf())
    private val dataOptionObserver = Observer<List<DataOption>> { list ->
        list?.let {
            dataOptionList.visibility = View.VISIBLE
            dataOptionListAdapter.updateDataOptionsList(it)
        }
    }
    private val dataOptionLoadingObserver = Observer<Boolean> { isLoading ->
        loading.visibility  = if (isLoading)View.VISIBLE else View.GONE
        if (isLoading) {
            dataOptionList.visibility =  View.GONE
            noDataText.visibility = View.GONE
        }
    }
    private val dataOptionNoDataObserver = Observer<Boolean> { isError ->
        noDataText.visibility  = if (isError)View.VISIBLE else View.GONE
        if (isError) {
            dataOptionList.visibility =  View.GONE
            loading.visibility = View.GONE
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        homeViewModel.dataOptions.observe(this,dataOptionObserver)
        homeViewModel.loading.observe(this,dataOptionLoadingObserver)
        homeViewModel.onNoData.observe(this,dataOptionNoDataObserver)
        homeViewModel.refresh()
        dataOptionList.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = dataOptionListAdapter
        }
        refreshDataOptionList.setOnRefreshListener {
            refreshData()
        }
    }

    private fun refreshData() {
        dataOptionList.visibility = View.GONE
        noDataText.visibility = View.GONE
        loading.visibility = View.VISIBLE
        homeViewModel.refresh()
        refreshDataOptionList.isRefreshing = false
    }

    override fun onResume() {
        super.onResume()
        refreshData()
    }
}