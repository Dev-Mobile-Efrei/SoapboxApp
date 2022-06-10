package fr.barbier.lyaet.soapboxapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;

import fr.barbier.lyaet.soapboxapp.core.infrastructure.DefaultDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        Thread threadInitDataBase = new Thread(() -> {
            String url = getResources().getString(R.string.database_url);
            String username = getResources().getString(R.string.database_username);
            String password = getResources().getString(R.string.database_password);
            DefaultDatabaseHelper defaultDatabaseHelper =
                    new DefaultDatabaseHelper(url,
                                              username,
                                              password);
            try {
                System.out.println("Initalizing db");
                defaultDatabaseHelper.startDatabasde();
                defaultDatabaseHelper.initRepositories();
            }catch(ClassNotFoundException | IllegalAccessException | InstantiationException throwables) {
                System.err.println(throwables.getMessage());
            } catch (SQLException throwables) {
                System.err.println(throwables.getSQLState());
                System.err.println(throwables.getMessage());
                System.err.println(throwables.getErrorCode());
            }
        });
        threadInitDataBase.start();

    }
}