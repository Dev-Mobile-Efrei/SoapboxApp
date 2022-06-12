package fr.barbier.lyaet.soapboxapp.core.Application.repository;

import com.j256.ormlite.dao.Dao;

import fr.barbier.lyaet.soapboxapp.core.Application.Business.GradeModel;

public class GradeRepository extends Repository<GradeModel> {
    private static GradeRepository instance;

    public static GradeRepository get()
    {
        if(instance != null) {
            return instance;
        }
        throw new RuntimeException("GraseRepository has not been initialised yet");
    }

    public GradeRepository(Dao<GradeModel, Integer> dao) {
        super(dao);
        instance = this;
    }
}
