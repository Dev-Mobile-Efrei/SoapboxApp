package fr.barbier.lyaet.soapboxapp.core.infrastructure;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import java.sql.SQLException;

import fr.barbier.lyaet.soapboxapp.core.domain.Race;

public class DefaultDatabaseHelper {

    private ConnectionSource connectionSource;

    public static DefaultDatabaseHelper instance;

    private Dao<Race, Integer> raceDao;

    public DefaultDatabaseHelper() {
        instance = this;
    }

    public void startDatabasde()
    {
        this.connectionSource = new JdbcConnectionSource();

        try {
            this.raceDao = DaoManager.createDao(this.connectionSource, Race.class);
        } catch (SQLException sqlException) {
            throw new RuntimeException("Error while initializing database: " + sqlException.getMessage());
        }
    }

    public Dao<Race, Integer> getRaceDao() {
        return this.raceDao;
    }
}
