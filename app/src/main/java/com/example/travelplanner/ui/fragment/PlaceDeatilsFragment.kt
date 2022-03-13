package com.example.travelplanner.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.travelplanner.R
import com.example.travelplanner.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.place_card_view.*


class PlaceDeatilsFragment:Fragment(R.layout.place_card_view){
    private val imagearr=arrayOf<Int>(R.drawable.error_image,R.drawable.image1,R.drawable.image2,R.drawable.image3)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val imageadapter:ViewPagerAdapter = ViewPagerAdapter();
        imageadapter.ViewPagerAdapter(requireContext(),imagearr)
        pagerview.adapter = imageadapter
        backtomain.setOnClickListener {
            findNavController().navigate(PlaceDeatilsFragmentDirections.actionPlaceDeatilsFragmentToMainFragment())
        }
        super.onViewCreated(view, savedInstanceState)
    }

}