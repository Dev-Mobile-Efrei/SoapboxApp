package fr.barbier.lyaet.soapboxapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import com.google.android.gms.maps.MapView;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

import fr.barbier.lyaet.soapboxapp.core.Application.Business.RaceModel;
import fr.barbier.lyaet.soapboxapp.core.Application.repository.RaceRepository;
import fr.barbier.lyaet.soapboxapp.location.service.LocationService;
import fr.barbier.lyaet.soapboxapp.location.service.TextEditLocationUpdater;

public class CreateRaceActivity extends AppCompatActivity {

    private MapView mapView;

    private EditText raceNameTextEdit;

    private Button createRaceButton;

    private CalendarView calendarView;

    private EditText addressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_race);
        this.raceNameTextEdit = this.findViewById(R.id.courseNameEditText);
        this.createRaceButton = this.findViewById(R.id.createRaceButton);
        this.calendarView = this.findViewById(R.id.calendarView);
        this.addressEditText = this.findViewById(R.id.addressEditText);
        Geocoder geocoder = new Geocoder(this.getApplicationContext());
        TextEditLocationUpdater textEditLocationUpdater = new TextEditLocationUpdater(this.addressEditText,geocoder);
        LocationService locationService = new LocationService(this, textEditLocationUpdater);
        locationService.requestForLocation();

    }

    public void createRace(View view)
    {
        if(this.raceNameTextEdit.getText().toString().isEmpty())
        {
            return;
        }

        Date date = new Date(this.calendarView.getDate());
        RaceModel raceModel = RaceModel.create(this.raceNameTextEdit.getText().toString(), date, this.addressEditText.getText().toString());

        Thread createRaceThread = new Thread(new Runnable() {

            @Override
            public void run() {
                RaceRepository.get().insert(raceModel);
            }
        });
        createRaceThread.start();
        this.returnToList();
    }

    public void cancelCreate(View view)
    {
        this.returnToList();
    }

    public void returnToList()
    {
        Intent intent = new Intent(this, RaceListActivity.class);
        this.startActivity(intent);
    }
}