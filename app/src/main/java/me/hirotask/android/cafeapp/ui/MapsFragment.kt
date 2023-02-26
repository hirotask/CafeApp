package me.hirotask.android.cafeapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch
import me.hirotask.android.cafeapp.R
import me.hirotask.android.cafeapp.domain.Cafe

class MapsFragment : Fragment(), OnMapReadyCallback {

    val cafeViewModel: CafeViewModel by activityViewModels()

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().setTitle(R.string.app_name)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0

        val tokyo = LatLng(36.0, 140.0)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                cafeViewModel.cafeList.collect {
                    for(cafe in it) {
                        Log.d(null, cafe.name)

                        mMap.addMarker(
                            MarkerOptions().title(cafe.name)
                                .position(LatLng(cafe.location.latitude, cafe.location.longitude))
                        )
                    }
                }

            }
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tokyo, 15f))


    }
}