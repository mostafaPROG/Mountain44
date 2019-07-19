package ir.nilgroup.mountain44.fragment

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ir.nilgroup.mountain44.R

class MapFragment : AppCompatActivity(), OnMapReadyCallback {

    private val Tag = "MapFragment"
    private val ERROR_DIALOG_REQUEST = 9001
    private val LOCATION_PERMISSION_REQUEST_CODE = 1234
    private var mLocationPermissionGranted: Boolean = false
    private lateinit var mMap: GoogleMap
    lateinit var mapStyle: FloatingActionButton

    private val FINE = android.Manifest.permission.ACCESS_FINE_LOCATION
    private val COARSE = android.Manifest.permission.ACCESS_COARSE_LOCATION
    var MAP_TYPE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_layout)

//        mapStyle = findViewById(R.id.mapStyle)
//        mapStyle.setOnClickListener {
//            when (MAP_TYPE) {
//                0 -> {
//                    mMap.mapType = GoogleMap.MAP_TYPE_NONE
//                    MAP_TYPE = 1
//                }
//                1 -> {
//                    mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
//                    MAP_TYPE = 2
//                }
//                2 -> {
//                    mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
//                    MAP_TYPE = 3
//                }
//                3 -> {
//                    mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
//                    MAP_TYPE = 0
//                }
//            }
//        }

        if (isServiceOk()) {
            getLocationPermission()
        }
    }

    fun initMap() {
        Log.d(Tag, "initMap: initializing map")
        val fragmentMap: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        fragmentMap.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        Toast.makeText(this, "map is ready", Toast.LENGTH_SHORT).show()
        Log.d(Tag, "onMapReady: map is ready")
        mMap = googleMap!!
    }

    fun getLocationPermission() {
        Log.d(Tag, "getLocationPermission: getting location permission")
        val permission: Array<out String> = arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (ContextCompat.checkSelfPermission(applicationContext, FINE) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    COARSE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                mLocationPermissionGranted = true
            } else {
                ActivityCompat.requestPermissions(this, permission, LOCATION_PERMISSION_REQUEST_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        Log.d(Tag, "onRequestPermissionsResult: called.")
        mLocationPermissionGranted = false
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.size > 0) {
                    for (i in grantResults.iterator()) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionGranted = false
                            println("onRequestPermissionsResult: permission failed")
                            return
                        }
                    }
                    println("onRequestPermissionsResult: permission granted")
                    mLocationPermissionGranted = true
                    initMap()
                }
            }
        }
    }

    @SuppressLint("ShowToast")
    fun isServiceOk(): Boolean {
        Log.i(Tag, "isServiceOk: checking google service version")
        val available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this@MapFragment)
        if (available == ConnectionResult.SUCCESS) {
            Log.i(Tag, "isServiceOk: google play service is working")
            return true
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Log.i(Tag, "isServiceOk: an error occured but we can fix it")
            val dialog =
                GoogleApiAvailability.getInstance().getErrorDialog(this@MapFragment, available, ERROR_DIALOG_REQUEST)
                    .show()
        } else {
            Toast.makeText(this, "شما نمی توانید نقشه را درخواست کنید", Toast.LENGTH_SHORT)
        }
        return false
    }
}