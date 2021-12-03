package com.example.myapplication.ui.fragments

import android.content.SharedPreferences
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener
import java.util.*


@Suppress("DEPRECATION")
class MapsFragment : Fragment(),OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationProviderClient : FusedLocationProviderClient
    var geoCoder: Geocoder? = null
    private var location: Location? = null
    private var googleApiClient: GoogleApiClient? = null
    var geocoder: Geocoder? = null
    var addresses: List<Address>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_maps, container, false)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        geocoder = Geocoder(requireActivity(), Locale.getDefault())


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(
            requireActivity(),
            OnSuccessListener<Any?> { location ->
             if (location != null) {

                }
            })

        return view
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.isMyLocationEnabled = true
        if (mMap != null) {
            mMap.setOnMyLocationChangeListener(object : GoogleMap.OnMyLocationChangeListener {
                override fun onMyLocationChange(arg0: Location) {
                    addresses = geocoder!!.getFromLocation(
                        arg0.getLatitude(),
                        arg0.getLongitude(),
                        1
                    )
                    val address = (addresses as MutableList<Address>?)!!.get(0)
                        .getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

                    val city = (addresses as MutableList<Address>?)!!.get(0).locality
                    val state = (addresses as MutableList<Address>?)!!.get(0).adminArea
                    val country = (addresses as MutableList<Address>?)!!.get(0).countryName
                    mMap.addMarker(
                        MarkerOptions().position(
                            LatLng(
                                arg0.getLatitude(),
                                arg0.getLongitude()
                            )

                        ).title(address)
                    )

                }
            })
        }
    }

}
