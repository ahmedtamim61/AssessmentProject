package com.example.androidassessment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.androidassessment.R
import com.example.androidassessment.model.Data
import com.example.androidassessment.model.Model
import com.example.androidassessment.viewmodel.AppViewModel

const val CACHED_DATA : String = "cachedData"
class ListFragment : Fragment() {

    private var toolbar : Toolbar? = null
    private lateinit var appViewModel : AppViewModel
    private var refreshLayout : SwipeRefreshLayout? = null
    private var recyclerView : RecyclerView? = null
    private var errorTextView : TextView? = null
    private var cachedData : Model? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        refreshLayout = view.findViewById(R.id.swipe_refresh_layout)
        recyclerView = view.findViewById(R.id.recycler_view)
        errorTextView = view.findViewById(R.id.error_text_view)
        toolbar = view.findViewById(R.id.toolbar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appViewModel = ViewModelProviders.of(requireActivity()).get(AppViewModel::class.java)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        refreshLayout?.setOnRefreshListener { refreshData() }
        cachedData = savedInstanceState?.getParcelable(CACHED_DATA)
        if (cachedData == null) {
            observeLiveData()
            refreshData()
        } else setDataToRecyclerView(cachedData!!.rows)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(CACHED_DATA, cachedData)
    }

    private fun refreshData() = appViewModel.fetchData()
    private fun observeLiveData() {
        appViewModel.getResponseLiveData()?.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                errorTextView?.visibility = View.GONE
                recyclerView?.visibility = View.VISIBLE
                if (refreshLayout!!.isRefreshing) refreshLayout!!.isRefreshing = false
                toolbar?.title = it.title
                setDataToRecyclerView(it.rows)
                cachedData = it
            }
        })

        appViewModel.getErrorLiveData()?.observe(viewLifecycleOwner, Observer {
            if (it != null && it) {
                if (refreshLayout!!.isRefreshing) refreshLayout!!.isRefreshing = false
                errorTextView?.visibility = View.VISIBLE
                recyclerView?.visibility = View.GONE
            }
        })
    }

    private fun setDataToRecyclerView(dataList : ArrayList<Data>) {
        recyclerView?.adapter = ListAdapter(requireContext(), dataList)
    }
}