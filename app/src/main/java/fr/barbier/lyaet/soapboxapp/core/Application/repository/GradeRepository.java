package fr.barbier.lyaet.soapboxapp.core.Application.repository;

import com.j256.ormlite.dao.Dao;

import fr.barbier.lyaet.soapboxapp.core.Application.Business.GradeModel;

public class GradeRepository extends Repository<GradeModel> {
    public static GradeRepository instance;

    public GradeRepository(Dao<GradeModel, Integer> dao) {
        super(dao);
        instance = this;
    }
}
