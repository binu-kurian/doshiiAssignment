package au.com.doshii.starwars.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import au.com.doshii.starwars.R
import au.com.doshii.starwars.view.adapter.DetailsListAdapter
import kotlinx.android.synthetic.main.fragment_details.*

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {
    private var listAdapter = DetailsListAdapter(hashMapOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val details = DetailsFragmentArgs.fromBundle(it).details
            if (details?.containsKey("Name")) {
                (activity as AppCompatActivity?)!!.supportActionBar!!.title = details?.getValue("Name") as CharSequence?
            } else {
                (activity as AppCompatActivity?)!!.supportActionBar!!.title = details?.getValue("Title") as CharSequence?
            }

            detailsList.apply {
                layoutManager = LinearLayoutManager(context)
                listAdapter.updateDetails(details)
                adapter = listAdapter

            }
        }
    }
}