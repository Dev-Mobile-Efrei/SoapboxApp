package fr.barbier.lyaet.soapboxapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import fr.barbier.lyaet.soapboxapp.core.Application.api.SoapboxApi;
import fr.barbier.lyaet.soapboxapp.location.service.LocationService;
import fr.barbier.lyaet.soapboxapp.location.service.TextViewLocationUpdater;

public class MainActivity extends AppCompatActivity {

    public void onBtnRaceList_Click(View view) {
        Intent intent = new Intent(this, RaceListActivity.class);
        this.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        String url = this.getResources().getString(R.string.database_url);
        String username = this.getResources().getString(R.string.database_username);
        String password = this.getResources().getString(R.string.database_password);

        SoapboxApi soapboxApi = new SoapboxApi(url, username, password, new ConsoleLogger());
        Thread apiThread = new Thread(soapboxApi);

        apiThread.start();

        LocationService locationService = new LocationService(this,new TextViewLocationUpdater(
                this.findViewById(R.id.resultTextbox)
        ));
        locationService.requestForLocation();
    }
}