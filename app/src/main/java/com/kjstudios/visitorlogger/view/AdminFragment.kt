package com.kjstudios.visitorlogger.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kjstudios.visitorlogger.R
import com.kjstudios.visitorlogger.viewmodel.FragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AdminFragment : Fragment(R.layout.admin_fragment) {

    private lateinit var viewModel: FragmentViewModel
    private lateinit var recyclerView: RecyclerView
    private val rvAdapter = VisitorRvAdapter(true)
    private val module = "admin"

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentViewModel::class.java)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        lifecycleScope.launch(Dispatchers.IO) {
            val log = withContext(Dispatchers.IO) {
                viewModel.getLoggedInUser(module)
            }
            if (log == null) {
                withContext(Dispatchers.Main) {
                    navigateToLogin()
                }
            }
        }
        recyclerView = view.findViewById(R.id.adminRecyclerView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rvAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.allVisitors.observe(viewLifecycleOwner) {
            it?.let { rvAdapter.submitList(it) }
        }
    }

    override fun onResume() {
        super.onResume()
        ItemTouchHelper(object : SwipeToDelete() {
            override fun onSwiped(
                viewHolder: RecyclerView.ViewHolder,
                direction: Int
            ) {
                val deletedVisitor = rvAdapter.getVisitor(viewHolder.adapterPosition)
                viewModel.deleteVisitor(deletedVisitor)
                Snackbar.make(recyclerView,deletedVisitor.name, Snackbar.LENGTH_LONG)
                    .setAction("Undo") { viewModel.restoreVisitor() }
                    .show()
            }
        }).attachToRecyclerView(recyclerView)
    }

    private fun navigateToLogin() {
        val action = AdminFragmentDirections.actionAdminFragmentToLoginFragment(module)
        navController.navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                viewModel.logoutUser(module)
                navigateToLogin()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
