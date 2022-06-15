package fr.barbier.lyaet.soapboxapp.location.service;

import android.location.Location;
import android.location.LocationListener;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class TextViewLocationUpdater implements LocationListener {

    private final TextView result;

    public TextViewLocationUpdater(TextView result) {
        this.result = result;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        this.result.setText(location.toString());

    }
}
