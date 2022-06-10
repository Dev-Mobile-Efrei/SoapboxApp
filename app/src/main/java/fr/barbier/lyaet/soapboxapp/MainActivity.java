package fr.barbier.lyaet.soapboxapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;

import fr.barbier.lyaet.soapboxapp.core.Application.Business.TeamModel;
import fr.barbier.lyaet.soapboxapp.core.Application.api.SoapboxApi;
import fr.barbier.lyaet.soapboxapp.core.Application.repository.RaceRepository;
import fr.barbier.lyaet.soapboxapp.core.Application.repository.TeamRepository;
import fr.barbier.lyaet.soapboxapp.core.domain.utils.Logger;
import fr.barbier.lyaet.soapboxapp.core.infrastructure.DefaultDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        String url = this.getResources().getString(R.string.database_url);
        String username = this.getResources().getString(R.string.database_username);
        String password = this.getResources().getString(R.string.database_password);

        SoapboxApi soapboxApi = new SoapboxApi(
                url,
                username,
                password,
                new ConsoleLogger());
        Thread apiThread = new Thread(soapboxApi);
        apiThread.start();

    }
}