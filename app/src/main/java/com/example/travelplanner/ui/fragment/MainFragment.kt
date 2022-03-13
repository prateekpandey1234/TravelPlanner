package com.example.travelplanner.ui.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.travelplanner.R
import com.example.travelplanner.databinding.MainfragmentBinding
import com.example.travelplanner.ui.startAnimation
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.mainfragment.*
import kotlinx.android.synthetic.main.saving_place_layout.*


class MainFragment :Fragment(),OnMapReadyCallback {
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var map1 : GoogleMap
    private lateinit var binding:MainfragmentBinding
    private var address:String? = null
    private lateinit var  animation: Animation
    private var location = "1245"

    private var mBottomSheetBehavior: BottomSheetBehavior<*>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainfragmentBinding.inflate(inflater,container,false)
        //code to add fab explosion
         animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fab_explode).apply {
            duration = 700
            interpolator = AccelerateDecelerateInterpolator()
        }

        binding.fab2.setOnClickListener {

            binding.explodeCircle.isVisible = true
            //setanimation from viewExt file
            binding.explodeCircle.startAnimation(animation){
                binding.root.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.base_color))
                //openinddialog box to save place
                SavePlaceDialog(it.parent as ViewGroup)
                binding.explodeCircle.isVisible = false
            }
        }
        return binding.root
    }
    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBottomSheetBehavior = BottomSheetBehavior.from(standard_bottom_sheet)
       bottomSheetCollapsed()
        mBottomSheetBehavior?.peekHeight =150
        //the height left after collapsed
        setBottomSheetAndCallBackBottomSheetBehaviour()
        //client for google maps
        fusedLocationProviderClient = FusedLocationProviderClient(this.requireActivity())
        mapView.getMapAsync {
            map1 = it
            map1.isMyLocationEnabled = true
            updatelocation()
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    location=query
                }
                placesadd()
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


        mapView.onCreate(savedInstanceState)

    }


    private fun setupSearchcall(){

//using shared element transition in a navigation controller
//        val extars =  FragmentNavigatorExtras(searchView to "searchview_transition")
//        setSearchViewOnClickListener(searchView,View.OnClickListener {
//            findNavController().navigate(
//                R.id.action_mainFragment_to_mapSearchFragment,
//                null,
//                null,
//                extars
//            )
//        })
    }
//setting up clickview listener for every child in the group
    private fun  setSearchViewOnClickListener( v:View,listener : View.OnClickListener) {
        if (v is ViewGroup) {
            val group :ViewGroup = v
            val count :Int = group.childCount
            for(i in 0 until count){
                val child :View = group.getChildAt(i)
                if(child is LinearLayout || child is RelativeLayout){
                    setSearchViewOnClickListener(child,listener)
                }
                if(child is TextView){
                    val text:TextView =  child
                    text.isFocusable= false
                }
                child.setOnClickListener(listener)
            }
        }
    }
    private fun setBottomSheetAndCallBackBottomSheetBehaviour() {
        place_item_layout.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToPlaceDeatilsFragment())

        }
        mBottomSheetBehavior?.state = BottomSheetBehavior.STATE_HIDDEN
        //callback
        mBottomSheetBehavior?.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback(){
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                Log.d("lolo","dkldkdkd")
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                if(mBottomSheetBehavior?.state == BottomSheetBehavior.STATE_HALF_EXPANDED && slideOffset>0){
                    bottomSheetExpand()
                }
                else if (slideOffset<=0){
                    bottomSheetCollapsed()
                }

            }

        })
    }
    private fun bottomSheetExpand() {
        mBottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun bottomSheetCollapsed() {
        mBottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
    }


    private fun moveCamer(latlng: LatLng,type:String){
        if(type == "Current"){
            map1.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,15f))
        }else{
            map1.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,10f))
        }

    }
    @SuppressLint("MissingPermission")
    private fun updatelocation(){
        val location = fusedLocationProviderClient.lastLocation
        location.addOnCompleteListener{Task1->
            if(Task1.isSuccessful){
                @NonNull
                var currLocation= Task1.result
                //cracking the address using latitude and longitude
                var loc = LatLng(currLocation.latitude, currLocation.longitude)
                var geocoder = Geocoder(this.requireContext())
                address = geocoder.getFromLocation(currLocation.latitude,currLocation.longitude,1)[0]
                    .getAddressLine(0)

                Toast.makeText(requireContext(),address,Toast.LENGTH_SHORT).show()
                moveCamer(loc,"Current")
            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(p0: GoogleMap) {
        map1 = p0

    }
    private fun SavePlaceDialog(viewGroup:ViewGroup){
        val builder = AlertDialog.Builder(requireContext())
        val dialogView: View =
            LayoutInflater.from(context).inflate(R.layout.saving_place_layout, viewGroup, false)
        builder.setView(dialogView)
        val alertDialog1 = builder.create()

        dialogView.findViewById<Button>(R.id.saveplacebtn).setOnClickListener {
            alertDialog1.hide()
        }
        alertDialog1.show()


    }
    private fun placesadd(){

        try {


            val gc = Geocoder(requireContext())
            val addresses: List<Address> =
                gc.getFromLocationName(location, 1) // get the found Address Objects
            val ll: MutableList<LatLng> =
                ArrayList(addresses.size) // A list to save the coordinates if they are available

            for (a in addresses) {
                if (a.hasLatitude() && a.hasLongitude()) {
                    ll.add(LatLng(a.latitude, a.longitude))
                    address = gc.getFromLocation(a.latitude,a.longitude,1)[0]
                        .getAddressLine(0)
                }
            }
            moveCamer(ll[0],";p;p")
            Toast.makeText(requireContext(),address,Toast.LENGTH_SHORT).show()
        }
        catch(e:Exception){
            Toast.makeText(requireContext(),"Place not found!! ${e.message}",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }




}