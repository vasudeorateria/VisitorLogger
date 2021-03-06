package com.kjstudios.visitorlogger.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kjstudios.visitorlogger.R
import com.kjstudios.visitorlogger.viewmodel.FragmentViewModel
import kotlinx.coroutines.*

class SecurityFragment : Fragment(R.layout.security_fragment) {

    private lateinit var viewModel: FragmentViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var addVisitor: FloatingActionButton
    private val rvAdapter = VisitorRvAdapter(false)

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentViewModel::class.java)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.securityRecyclerView)
        addVisitor = view.findViewById(R.id.addVisitor)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rvAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.allVisitors.observe(viewLifecycleOwner) { it?.let { rvAdapter.submitList(it) } }
    }

    override fun onResume() {
        super.onResume()
        addVisitor.setOnClickListener {
            AddVisitorFragment().show(
                childFragmentManager,
                "Add Visitor"
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                viewModel.logoutUser()
                navController.navigateUp()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}

