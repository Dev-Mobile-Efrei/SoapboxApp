package fr.barbier.lyaet.soapboxapp.core.infrastructure;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.jdbc.db.MysqlDatabaseType;
import com.j256.ormlite.table.TableUtils;
import fr.barbier.lyaet.soapboxapp.core.Application.Business.*;
import fr.barbier.lyaet.soapboxapp.core.Application.repository.*;

import java.sql.SQLException;

public class DefaultDatabaseHelper {

    public static DefaultDatabaseHelper instance;

    private final String password;

    private Dao<GradeModel, Integer> gradeDao;

    private final String url;

    private Dao<ParticipationModel, Integer> participationDao;

    private final String userName;

    private JdbcPooledConnectionSource connectionSource;

    private Dao<MemberModel, Integer> memberDaeo;

    private Dao<RaceModel, Integer> raceDao;

    private Dao<TeamModel, Integer> teamDao;

    public DefaultDatabaseHelper(String dataBaseUrl, String dataBaseUserName, String dataBasePassword) {
        instance = this;
        this.url = dataBaseUrl;
        this.userName = dataBaseUserName;
        this.password = dataBasePassword;
    }

    public void initRepositories() {
        new GradeRepository(this.gradeDao);
        new MemberRepository(this.memberDaeo);
        new ParticipationRepository(this.participationDao);
        new RaceRepository(this.raceDao);
        new TeamRepository(this.teamDao);
    }

    public void startDatabasde() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        this.connectionSource = new JdbcPooledConnectionSource(this.url, new MysqlDatabaseType());
        this.connectionSource.setUsername(this.userName);
        this.connectionSource.setPassword(this.password);

        try {
            this.tablesInitializer();
            this.daoInitalizer();
        } catch (SQLException sqlException) {
            System.err.println(sqlException);
            System.err.println(sqlException.getMessage());
            System.err.println(sqlException.getSQLState());
            throw new RuntimeException("Error while initializing database: " + sqlException.getMessage());
        }
    }

    private void daoInitalizer() throws SQLException {
        this.raceDao = DaoManager.createDao(this.connectionSource, RaceModel.class);
        this.teamDao = DaoManager.createDao(this.connectionSource, TeamModel.class);
        this.memberDaeo = DaoManager.createDao(this.connectionSource, MemberModel.class);
        this.gradeDao = DaoManager.createDao(this.connectionSource, GradeModel.class);
        this.participationDao = DaoManager.createDao(this.connectionSource, ParticipationModel.class);
    }

    private void tablesInitializer() throws SQLException {
        TableUtils.createTableIfNotExists(this.connectionSource, RaceModel.class);
        TableUtils.createTableIfNotExists(this.connectionSource, TeamModel.class);
        TableUtils.createTableIfNotExists(this.connectionSource, MemberModel.class);
        TableUtils.createTableIfNotExists(this.connectionSource, GradeModel.class);
        TableUtils.createTableIfNotExists(this.connectionSource, ParticipationModel.class);
    }
}
