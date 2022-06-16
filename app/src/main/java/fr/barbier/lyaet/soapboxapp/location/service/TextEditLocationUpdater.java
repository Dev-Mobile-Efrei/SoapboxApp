package fr.barbier.lyaet.soapboxapp.location.service;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.List;

public class TextEditLocationUpdater implements LocationListener {

    private final EditText result;

    private Geocoder geocoder;

    public TextEditLocationUpdater(EditText result, Geocoder geocoder) {
        this.result = result;
        this.geocoder = geocoder;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        try {
            StringBuilder stringBuilder = new StringBuilder(0);
            List<Address> adressList = this.geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            adressList.forEach(address ->{
                    stringBuilder.append(address.getCountryName()).append(" ").append(address.getLocality()).append(" ")
                    .append(address.getAdminArea()).append(" ");
                    stringBuilder.append(address.getPostalCode());
            });
            this.result.setText(stringBuilder.toString());
        } catch (IOException e) {

        }


    }
}
