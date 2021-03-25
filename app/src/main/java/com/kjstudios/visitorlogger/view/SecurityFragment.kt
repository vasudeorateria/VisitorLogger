package com.kjstudios.visitorlogger.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.kjstudios.visitorlogger.AdminViewModel
import com.kjstudios.visitorlogger.R
import com.kjstudios.visitorlogger.Visitor
import java.util.*


class SecurityFragment : Fragment(R.layout.add_new_visitor) {

    private lateinit var visitorName: TextInputEditText
    private lateinit var visitorContact: TextInputEditText
    private lateinit var visitorConcernedPerson: TextInputEditText
    private lateinit var visitorPurpose: TextInputEditText
    private lateinit var visitorAddress: TextInputEditText
    private lateinit var visitorVehicleNumber: TextInputEditText
    private lateinit var addVisitor: Button
    private lateinit var viewModel: AdminViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdminViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        visitorName = view.findViewById(R.id.newVisitorName)
        visitorContact = view.findViewById(R.id.newVisitorContact)
        visitorConcernedPerson = view.findViewById(R.id.newVisitorConcernedPerson)
        visitorPurpose = view.findViewById(R.id.newVisitorPurpose)
        visitorAddress = view.findViewById(R.id.newVisitorAddress)
        visitorVehicleNumber = view.findViewById(R.id.newVisitorVehicleNumber)
        addVisitor = view.findViewById(R.id.addVisitor)
    }

    override fun onResume() {
        super.onResume()
        addVisitor.setOnClickListener {
            if (checkInputs()) {
                viewModel.addVisitor(
                    Visitor(
                        Date().time.toString(),
                        visitorName.text.toString(),
                        visitorContact.text.toString(),
                        visitorConcernedPerson.text.toString(),
                        visitorPurpose.text.toString(),
                        visitorAddress.text.toString(),
                        visitorVehicleNumber.text.toString()
                    )
                )
                Toast.makeText(context, "insertiong item", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkInputs(): Boolean {
        if (visitorName.text.isNullOrBlank() || visitorContact.text.isNullOrBlank() ||
            visitorConcernedPerson.text.isNullOrBlank() || visitorPurpose.text.isNullOrBlank() ||
            visitorAddress.text.isNullOrBlank() || visitorVehicleNumber.text.isNullOrBlank()
        ) {
            return false
        }
        return true
    }
}