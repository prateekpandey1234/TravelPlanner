package com.example.travelplanner.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.travelplanner.R
import kotlinx.android.synthetic.main.register_layout.*

class RegisterFragement: Fragment(R.layout.register_layout) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        register_finish_btn.setOnClickListener {
            findNavController().navigate(RegisterFragementDirections.actionRegisterFragementToMainFragment())
        }
    }
}