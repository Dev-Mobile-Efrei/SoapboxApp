package fr.barbier.lyaet.soapboxapp.core.Application.repository;

import com.j256.ormlite.dao.Dao;

import fr.barbier.lyaet.soapboxapp.core.Application.Business.TeamModel;

public class TeamRepository extends Repository<TeamModel> {
    private static TeamRepository instance;

    public static TeamRepository get()
    {
        if(instance != null) {
            return instance;
        }
        throw new RuntimeException("TeamRepository has not been initialised yet");
    }
    public TeamRepository(Dao<TeamModel, Integer> dao) {
        super(dao);
        instance = this;
    }
}
