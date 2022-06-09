package fr.barbier.lyaet.soapboxapp.core.Application.repository;

import com.j256.ormlite.dao.Dao;

import fr.barbier.lyaet.soapboxapp.core.Application.Business.RaceModel;

public class RaceRepository extends Repository<RaceModel>{
    public static RaceRepository instance;

    public RaceRepository(Dao<RaceModel, Integer> dao) {
        super(dao);
        instance = this;
    }
}
