package com.example.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import net.daum.mf.map.api.MapView


class map : AppCompatActivity() {

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map)

        val menu_btn = findViewById<Button>(R.id.butMenu)
        val tomain_btn = findViewById<Button>(R.id.tomain)
        val tobook_btn = findViewById<Button>(R.id.tobook)
        val topharmacy_btn = findViewById<Button>(R.id.topharmacy)

        val intent_toexplain = Intent(this, explain::class.java)
        val intent_tomain = Intent(this, MainActivity::class.java)
        val intent_tobook = Intent(this, like_med::class.java)
        val intent_topharmacy = Intent(this, map::class.java)

        //메뉴 바 클릭시 펼쳐짐
        menu_btn.setOnClickListener {
            val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
            if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                drawerLayout.openDrawer(Gravity.LEFT)
            } else {
                drawerLayout.closeDrawer(Gravity.LEFT)
            }
        }


        // to main
        tomain_btn.setOnClickListener {
            startActivity(intent_tomain)
        }

        // to book
        tobook_btn.setOnClickListener {
            startActivity(intent_tobook)
        }

        // to pharmacy
        topharmacy_btn.setOnClickListener {
            startActivity(intent_topharmacy)
        }


        val permissionlistener: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
//                Toast.makeText(this@map, "Permission Granted", Toast.LENGTH_SHORT).show()
                val lm: LocationManager =
                    getSystemService(Context.LOCATION_SERVICE) as LocationManager;
                val gpsLocationListener: LocationListener = object : LocationListener {
                    override fun onLocationChanged(location: Location) {
                        val provider = location.provider
                        val lon = location.longitude
                        val lat = location.latitude
                        val altitude = location.altitude
                        val browserIntent =
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("kakaomap://search?q=약국&p=$lat,$lon")
                            )
                        startActivity(browserIntent)
                    }

                    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
                    override fun onProviderEnabled(provider: String) {}
                    override fun onProviderDisabled(provider: String) {}
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    lm.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        1000,
                        1f,
                        gpsLocationListener
                    )
                    lm.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        1000,
                        1f,
                        gpsLocationListener
                    )
                }
            }

            override fun onPermissionDenied(deniedPermissions: List<String?>) {
                Toast.makeText(
                    this@map,
                    "Permission Denied\n$deniedPermissions",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        TedPermission.create()
            .setPermissionListener(permissionlistener)
            .setRationaleMessage("카카오맵을 사용하기 위해서는 위치 접근 권한이 필요해요")
            .setDeniedMessage("왜 거부하셨어요...\n하지만 [설정] > [권한] 에서 권한을 허용할 수 있어요.")
            .setPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ).check()

        val mapView = MapView(this)

        val mapViewContainer = findViewById<View>(R.id.map_view) as ViewGroup
        mapViewContainer.addView(mapView)


    }

}
