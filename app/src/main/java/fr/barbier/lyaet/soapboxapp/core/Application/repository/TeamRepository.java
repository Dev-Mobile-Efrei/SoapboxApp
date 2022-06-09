package fr.barbier.lyaet.soapboxapp.core.Application.repository;

import com.j256.ormlite.dao.Dao;

import fr.barbier.lyaet.soapboxapp.core.Application.Business.TeamModel;

public class TeamRepository extends Repository<TeamModel> {
    public static TeamRepository instance;

    public TeamRepository(Dao<TeamModel, Integer> dao) {
        super(dao);
        instance = this;
    }
}
