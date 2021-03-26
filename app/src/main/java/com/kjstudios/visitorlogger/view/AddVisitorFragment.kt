package com.kjstudios.visitorlogger.view

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.kjstudios.visitorlogger.R
import com.kjstudios.visitorlogger.model.Visitor
import com.kjstudios.visitorlogger.viewmodel.FragmentViewModel
import java.util.*

class AddVisitorFragment : DialogFragment() {

    private lateinit var visitorName: TextInputEditText
    private lateinit var visitorContact: TextInputEditText
    private lateinit var visitorConcernedPerson: TextInputEditText
    private lateinit var visitorPurpose: TextInputEditText
    private lateinit var visitorAddress: TextInputEditText
    private lateinit var visitorVehicleNumber: TextInputEditText
    private lateinit var addVisitor: Button
    private lateinit var viewModel: FragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.add_new_visitor, container, false)
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

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onResume() {
        super.onResume()
        addVisitor.setOnClickListener {
            if (checkInputs()) {
                val dateFormat = "dd MMM yyyy hh:mm a"
                val date = DateFormat.format(dateFormat, Date()) as String
                val visitor = Visitor(
                    date,
                    visitorName.text.toString(),
                    visitorContact.text.toString(),
                    visitorConcernedPerson.text.toString(),
                    visitorPurpose.text.toString(),
                    visitorAddress.text.toString(),
                    visitorVehicleNumber.text.toString()
                )
                viewModel.addVisitor(visitor)
                dialog?.dismiss()
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