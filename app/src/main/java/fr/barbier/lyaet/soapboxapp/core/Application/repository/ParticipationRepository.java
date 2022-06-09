package fr.barbier.lyaet.soapboxapp.core.Application.repository;

import com.j256.ormlite.dao.Dao;

import fr.barbier.lyaet.soapboxapp.core.Application.Business.ParticipationModel;

public class ParticipationRepository extends Repository<ParticipationModel> {
    public static ParticipationRepository instance;

    public ParticipationRepository(Dao<ParticipationModel, Integer> dao) {
        super(dao);
        instance = this;
    }
}
