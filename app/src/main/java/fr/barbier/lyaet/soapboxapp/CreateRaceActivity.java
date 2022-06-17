package fr.barbier.lyaet.soapboxapp;

import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.MapView;
import fr.barbier.lyaet.soapboxapp.core.Application.Business.RaceModel;
import fr.barbier.lyaet.soapboxapp.core.Application.repository.RaceRepository;
import fr.barbier.lyaet.soapboxapp.location.service.LocationService;
import fr.barbier.lyaet.soapboxapp.location.service.TextEditLocationUpdater;

import java.util.Date;

public class CreateRaceActivity extends AppCompatActivity {

    private EditText addressEditText;

    private CalendarView calendarView;

    private Button createRaceButton;

    private MapView mapView;

    private EditText raceNameTextEdit;

    public void createRace(View view) {
        if (this.raceNameTextEdit.getText().toString().isEmpty()) {
            return;
        }

        Date date = new Date(this.calendarView.getDate());
        RaceModel raceModel = RaceModel.create(this.raceNameTextEdit.getText()
                                                                    .toString(), date, this.addressEditText.getText()
                                                                                                           .toString());

        Thread createRaceThread = new Thread(() -> RaceRepository.get().insert(raceModel));
        createRaceThread.start();
        this.returnToList();
    }

    public void returnToList() {
        Intent intent = new Intent(this, RaceListActivity.class);
        this.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_create_race);
        this.raceNameTextEdit = this.findViewById(R.id.courseNameEditText);
        this.createRaceButton = this.findViewById(R.id.btnCreateRace);
        this.calendarView = this.findViewById(R.id.calendarView);
        this.addressEditText = this.findViewById(R.id.addressEditText);
        Geocoder geocoder = new Geocoder(this.getApplicationContext());
        TextEditLocationUpdater textEditLocationUpdater = new TextEditLocationUpdater(this.addressEditText, geocoder);
        LocationService locationService = new LocationService(this, textEditLocationUpdater);
        locationService.requestForLocation();
    }
}