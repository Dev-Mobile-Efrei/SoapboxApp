package fr.barbier.lyaet.soapboxapp.core.infrastructure;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

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

public class DefaultDatabaseHelper {

    private ConnectionSource connectionSource;

    public static DefaultDatabaseHelper instance;

    private Dao<RaceModel, Integer> raceDao;
    private Dao<TeamModel, Integer> teamDao;
    private Dao<MemberModel, Integer> memberDaeo;
    private Dao<GradeModel, Integer> gradeDao;
    private Dao<ParticipationModel, Integer> participationDao;

    public DefaultDatabaseHelper() {
        instance = this;
    }

    public void startDatabasde()
    {
        this.connectionSource = new JdbcConnectionSource();

        try {
            this.raceDao = DaoManager.createDao(this.connectionSource, RaceModel.class);
            this.teamDao = DaoManager.createDao(this.connectionSource, TeamModel.class);
            this.memberDaeo = DaoManager.createDao(this.connectionSource, MemberModel.class);
            this.gradeDao = DaoManager.createDao(this.connectionSource, GradeModel.class);
            this.participationDao = DaoManager.createDao(this.connectionSource, ParticipationModel.class);
        } catch (SQLException sqlException) {
            throw new RuntimeException("Error while initializing database: " + sqlException.getMessage());
        }
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
