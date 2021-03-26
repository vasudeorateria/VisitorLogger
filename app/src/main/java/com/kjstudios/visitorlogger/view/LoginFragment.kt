package com.kjstudios.visitorlogger.view

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputEditText
import com.kjstudios.visitorlogger.R
import com.kjstudios.visitorlogger.model.User
import com.kjstudios.visitorlogger.viewmodel.FragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.regex.Pattern


class LoginFragment : Fragment(R.layout.login) {

    private lateinit var viewModel: FragmentViewModel
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
        viewModel = ViewModelProvider(this).get(FragmentViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        email = view.findViewById(R.id.email)
        password = view.findViewById(R.id.password)
        login = view.findViewById(R.id.login)
    }

    override fun onResume() {
        super.onResume()
        login.setOnClickListener {
            hideKeyboard(login)
            if (checkInputs()) {
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.loginUser(User(email.text.toString(), password.text.toString(), module, -1))
                    withContext(Dispatchers.Main) {
                        navController.popBackStack()
                    }
                }
            }
        }
    }

    private fun hideKeyboard(view: View) {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun checkInputs(): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches() ||
            password.text.isNullOrBlank()
        ) {
            return false
        }

        val PASSWORD_PATTERN = "^[a-zA-Z0-9]*$"
        return Pattern.compile(PASSWORD_PATTERN).matcher(password.text.toString()).matches()
    }

}