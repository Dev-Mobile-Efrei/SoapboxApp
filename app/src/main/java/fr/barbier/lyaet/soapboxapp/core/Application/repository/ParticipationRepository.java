package fr.barbier.lyaet.soapboxapp.core.Application.repository;

import com.j256.ormlite.dao.Dao;

import fr.barbier.lyaet.soapboxapp.core.Application.Business.ParticipationModel;

public class ParticipationRepository extends Repository<ParticipationModel> {
    private static ParticipationRepository instance;

    public static ParticipationRepository get()
    {
        if(instance != null) {
            return instance;
        }
        throw new RuntimeException("ParticipationRepository has not been initialised yet");
    }

    public ParticipationRepository(Dao<ParticipationModel, Integer> dao) {
        super(dao);
        instance = this;
    }
}
