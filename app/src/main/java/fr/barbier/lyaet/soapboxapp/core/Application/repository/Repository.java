package fr.barbier.lyaet.soapboxapp.core.Application.repository;

import com.j256.ormlite.dao.Dao;

import fr.barbier.lyaet.soapboxapp.core.Application.Business.BasicModel;

public abstract class Repository<T extends BasicModel> {
    protected Dao<T, Integer> dao;

    public Dao<T, Integer> getDao() {
        return this.dao;
    }

    public Repository(Dao<T, Integer> dao) {
        this.dao = dao;
    }
}
