package com.kjstudios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kjstudios.visitorlogger.AdminViewModel
import com.kjstudios.visitorlogger.R
import com.kjstudios.visitorlogger.VisitorRvAdapter

class AdminFragment : Fragment(R.layout.admin_fragment) {

    private lateinit var viewModel: AdminViewModel
    private lateinit var recyclerView: RecyclerView
    private val rvAdapter = VisitorRvAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdminViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.adminRecyclerView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rvAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.allVisitors.observe(viewLifecycleOwner) { visitorsList ->
            visitorsList?.let {
                rvAdapter.updateVisitors(it)
                println(it)
            }
        }
    }
}