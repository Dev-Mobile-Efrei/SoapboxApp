package fr.barbier.lyaet.soapboxapp.core.Application.api;

import java.sql.SQLException;

import fr.barbier.lyaet.soapboxapp.core.domain.utils.Logger;
import fr.barbier.lyaet.soapboxapp.core.infrastructure.DefaultDatabaseHelper;

public class SoapboxApi implements Runnable{

    private final String url;

    private final String username;

    private final String password;

    private DefaultDatabaseHelper databaseHelper;

    private Logger logger;

    public SoapboxApi(String url,
                      String username,
                      String password,
                      Logger logger) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.logger = logger;
    }

    @Override
    public void run() {
        this.databaseHelper =
                new DefaultDatabaseHelper(this.url, this.username, this.password);
        try {
            this.logger.log("Initalizing db");
            this.databaseHelper.startDatabasde();
            this.databaseHelper.initRepositories();
        }catch(ClassNotFoundException | IllegalAccessException | InstantiationException throwables) {
            this.logger.log(throwables.getMessage());
        } catch (SQLException throwables) {
            this.logger.log(throwables.getSQLState());
            this.logger.log(throwables.getMessage());
            this.logger.log(String.valueOf(throwables.getErrorCode()));
        }
    }
}
