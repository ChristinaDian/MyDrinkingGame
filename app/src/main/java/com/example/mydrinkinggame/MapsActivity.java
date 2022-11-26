package com.example.mydrinkinggame;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.mydrinkinggame.databinding.ActivityMapsBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    FusedLocationProviderClient client;
    SupportMapFragment supportMapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);

        //Initialize fused location
        client = LocationServices.getFusedLocationProviderClient(this);


    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    23);
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    supportMapFragment.getMapAsync((new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                            MarkerOptions options = new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker
                                    (BitmapDescriptorFactory.HUE_CYAN)).alpha(0.7f).position(latLng).title("Current Location");

                            //Zoom map
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

                            //Add marker on map
                            googleMap.addMarker(options);
                        }
                    }));
                }
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Map<String, LatLng> barsNear = new HashMap<>();
        barsNear.put("Bushido Club", new LatLng(42.141949, 24.780722));
        barsNear.put("Onyx by Payner Club", new LatLng(42.142488, 24.780232));
        barsNear.put("W Garden Club", new LatLng(42.142275, 24.780427));
        barsNear.put("Plovdiv Event Center", new LatLng(42.14214, 24.78769));
        barsNear.put("Antik Premium Club", new LatLng(42.15378, 24.75042));
        barsNear.put("Club VOID", new LatLng(42.14977, 24.74778));
        barsNear.put("No Sense Club", new LatLng(42.14930, 24.74808));
        barsNear.put("Rock Bar Download", new LatLng(42.14719, 24.74747));
        barsNear.put("Fabric Bar", new LatLng(42.14545, 24.75057));
        barsNear.put("Club Fargo", new LatLng(42.14263, 24.74541));
        barsNear.put("Secrets Club", new LatLng(42.15850, 24.73539));
        for(int i=0; i< barsNear.size(); i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                barsNear.forEach((k, v) -> mMap.addMarker(new MarkerOptions().position(barsNear.get(k)).title(k)));
            }

        }
        // Add a marker in Sydney and move the camera
      /*
       LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        */
       getCurrentLocation();

    }

}