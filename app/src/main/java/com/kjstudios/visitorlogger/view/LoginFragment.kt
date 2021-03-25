package com.kjstudios.visitorlogger.view

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputEditText
import com.kjstudios.visitorlogger.R
import com.kjstudios.visitorlogger.model.User
import com.kjstudios.visitorlogger.viewmodel.AdminViewModel

class LoginFragment : Fragment(R.layout.login) {

    private lateinit var viewModel: AdminViewModel
    private val navController: NavController by lazy {
        findNavController()
    }

    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var login: Button

    private val module by lazy {
        val args: LoginFragmentArgs by navArgs()
        args.module
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdminViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        email = view.findViewById(R.id.email)
        password = view.findViewById(R.id.password)
        login = view.findViewById(R.id.login)
    }

    override fun onResume() {
        super.onResume()
        login.setOnClickListener {
            println(password.toString())
            if (checkInputs()) {
                println(User(email.text.toString(), password.text.toString(), module, -1))
                viewModel.loginUser(User(email.text.toString(), password.text.toString(), module,-1))
                navController.popBackStack()
            }
        }
    }

    private fun checkInputs(): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString())
                .matches() || password.text.isNullOrBlank()
        ) {
            return false
        }
        return true
    }

}