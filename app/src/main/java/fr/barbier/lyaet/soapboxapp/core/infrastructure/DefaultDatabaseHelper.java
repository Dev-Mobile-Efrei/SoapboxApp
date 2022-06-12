package fr.barbier.lyaet.soapboxapp.core.infrastructure;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.jdbc.db.MysqlDatabaseType;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import fr.barbier.lyaet.soapboxapp.core.Application.Business.GradeModel;
import fr.barbier.lyaet.soapboxapp.core.Application.Business.MemberModel;
import fr.barbier.lyaet.soapboxapp.core.Application.Business.ParticipationModel;
import fr.barbier.lyaet.soapboxapp.core.Application.Business.RaceModel;
import fr.barbier.lyaet.soapboxapp.core.Application.Business.TeamModel;
import fr.barbier.lyaet.soapboxapp.core.Application.repository.GradeRepository;
import fr.barbier.lyaet.soapboxapp.core.Application.repository.MemberRepository;
import fr.barbier.lyaet.soapboxapp.core.Application.repository.ParticipationRepository;
import fr.barbier.lyaet.soapboxapp.core.Application.repository.RaceRepository;
import fr.barbier.lyaet.soapboxapp.core.Application.repository.TeamRepository;

public class DefaultDatabaseHelper  {

    private JdbcPooledConnectionSource connectionSource;

    public static DefaultDatabaseHelper instance;

    private Dao<RaceModel, Integer> raceDao;
    private Dao<TeamModel, Integer> teamDao;
    private Dao<MemberModel, Integer> memberDaeo;
    private Dao<GradeModel, Integer> gradeDao;
    private Dao<ParticipationModel, Integer> participationDao;

    private String url;

    private String userName;

    private String password;

    public DefaultDatabaseHelper(String dataBaseUrl, String dataBaseUserName,String dataBasePassword) {
        instance = this;
        this.url = dataBaseUrl;
        this.userName = dataBaseUserName;
        this.password = dataBasePassword;
    }

    public void startDatabasde() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        this.connectionSource = new JdbcPooledConnectionSource(
                "jdbc:mysql://10.0.2.2:3306/soapbox",
                new MysqlDatabaseType()
        );
        this.connectionSource.setUsername(this.userName);
        this.connectionSource.setPassword(this.password);

        try {
            this.tablesInitializer();
            this.daoInitalizer();

        } catch (SQLException sqlException) {
            System.err.println(sqlException.toString());
            System.err.println(sqlException.getMessage());
            System.err.println(sqlException.getSQLState());
            throw new RuntimeException("Error while initializing database: " + sqlException.getMessage());
        }
    }

    private void tablesInitializer() throws SQLException {
        TableUtils.createTableIfNotExists(this.connectionSource, RaceModel.class);
        TableUtils.createTableIfNotExists(this.connectionSource, TeamModel.class);
        TableUtils.createTableIfNotExists(this.connectionSource, MemberModel.class);
        TableUtils.createTableIfNotExists(this.connectionSource, GradeModel.class);
        TableUtils.createTableIfNotExists(this.connectionSource, ParticipationModel.class);

    }
    
    private void daoInitalizer() throws SQLException {
        this.raceDao = DaoManager.createDao(this.connectionSource, RaceModel.class);
        this.teamDao = DaoManager.createDao(this.connectionSource, TeamModel.class);
        this.memberDaeo = DaoManager.createDao(this.connectionSource, MemberModel.class);
        this.gradeDao = DaoManager.createDao(this.connectionSource, GradeModel.class);
        this.participationDao = DaoManager.createDao(this.connectionSource, ParticipationModel.class);
    }

    public void initRepositories()
    {
        new GradeRepository(this.gradeDao);
        new MemberRepository(this.memberDaeo);
        new ParticipationRepository(this.participationDao);
        new RaceRepository(this.raceDao);
        new TeamRepository(this.teamDao);
    }

}
