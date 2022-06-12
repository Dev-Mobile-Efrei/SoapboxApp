package fr.barbier.lyaet.soapboxapp.core.Application.repository;

import com.j256.ormlite.dao.Dao;

import fr.barbier.lyaet.soapboxapp.core.Application.Business.RaceModel;

public class RaceRepository extends Repository<RaceModel>{
    private static RaceRepository instance;

    public static RaceRepository get()
    {
        if(instance != null) {
            return instance;
        }
        throw new RuntimeException("RaceRepository has not been initialised yet");
    }

    public RaceRepository(Dao<RaceModel, Integer> dao) {
        super(dao);
        instance = this;
    }
}
