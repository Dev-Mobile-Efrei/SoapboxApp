package fr.barbier.lyaet.soapboxapp.location.service;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class LocationService {

    private final LocationManager locationManager;

    private final AppCompatActivity appCompatActivity;

    private String provider = "";

    private final LocationListener locationListenerCallBack;

    public LocationService(AppCompatActivity appCompatActivity, LocationListener locationListenerCallBack) {
        this.locationManager = (LocationManager) appCompatActivity.getSystemService(Context.LOCATION_SERVICE);
        this.appCompatActivity = appCompatActivity;
        this.locationListenerCallBack = locationListenerCallBack;

        if (this.hasCoarseLocationPermission()) {
            this.provider = LocationManager.FUSED_PROVIDER;
        }
        if (this.hasFineLocationPermission()) {
            this.provider = LocationManager.GPS_PROVIDER;
        }
    }

    private void requestLocationPermission() {
        ActivityResultLauncher<String[]> locationPermissionRequest = this.appCompatActivity.registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {

            Boolean fineLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false);

            Boolean coarseLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false);

            if (fineLocationGranted != null && fineLocationGranted) {
                this.provider = LocationManager.GPS_PROVIDER;
            } else if (coarseLocationGranted != null && coarseLocationGranted) {
                this.provider = LocationManager.FUSED_PROVIDER;
            } else {
                return;
            }
            this.requestForLocation();
        });
        locationPermissionRequest.launch(new String[] { Manifest.permission.ACCESS_FINE_LOCATION,
                                                        Manifest.permission.ACCESS_COARSE_LOCATION });
    }

    public boolean hasFineLocationPermission() {
        return ActivityCompat.checkSelfPermission(this.appCompatActivity.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
               PackageManager.PERMISSION_GRANTED;
    }

    public boolean hasCoarseLocationPermission() {
        return ActivityCompat.checkSelfPermission(this.appCompatActivity.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
               PackageManager.PERMISSION_GRANTED;
    }

    public void requestForLocation() {

        if (ActivityCompat.checkSelfPermission(this.appCompatActivity.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this.appCompatActivity.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED) {
            this.requestLocationPermission();
        } else {
            this.locationManager.requestLocationUpdates(this.provider, 0, 0, this.locationListenerCallBack);
        }
    }
}
