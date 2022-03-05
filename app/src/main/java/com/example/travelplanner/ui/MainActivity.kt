package com.example.travelplanner.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.travelplanner.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

class MainActivity : AppCompatActivity() {
    private var locationGranted:Boolean= false
    private var fine_location :String = Manifest.permission.ACCESS_FINE_LOCATION
    private var coarse_location :String = Manifest.permission.ACCESS_COARSE_LOCATION
    private var LOCATION_PERMISSION_REQUEST_CODE:Int = 1234
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermissions()
    }
    private fun requestPermissions(){
        var array1 = arrayOf(fine_location,coarse_location)
        if(ContextCompat.checkSelfPermission(this,
                fine_location) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this,
                    coarse_location) == PackageManager.PERMISSION_GRANTED){
                locationGranted = true
            }
            else{
                ActivityCompat.requestPermissions(this ,
                    array1,
                    LOCATION_PERMISSION_REQUEST_CODE)
            }
        }else{
            ActivityCompat.requestPermissions(this ,
                array1,
                LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        locationGranted = false
        if(grantResults.isNotEmpty()){
            for(element in grantResults){
                if(element != PackageManager.PERMISSION_GRANTED) {
                    locationGranted = false
                    return
                }
            }
        }
        locationGranted = true
    }

}