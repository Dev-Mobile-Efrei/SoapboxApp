package fr.barbier.lyaet.soapboxapp.core.Application.repository;

import androidx.annotation.Nullable;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

import fr.barbier.lyaet.soapboxapp.core.Application.Business.BasicModel;

public abstract class Repository<T extends BasicModel> {
    protected Dao<T, Integer> dao;

    protected Dao<T, Integer> getDao() {
        return this.dao;
    }

    protected Repository(Dao<T, Integer> dao) {
        this.dao = dao;
    }

    @Nullable
    public T getById(long id)
    {
        try {
            return this.dao.queryForId((int) id);
        } catch (SQLException throwables) {
            System.err.println("");
        }
        return null;
    }
    public Collection<T> getAll()
    {
        try {
            return this.dao.queryForAll();
        } catch (SQLException throwables) {
            System.err.println("");
        }
        return Collections.emptyList();
    }
    @Nullable
    public T insert(T object)
    {
        try {
            this.dao.create(object);
        } catch (SQLException throwables) {
            System.err.println("");
        }
        return null;
    }

    @Nullable
    public T delete(T object)
    {
        try {
            int success = this.dao.delete(object);
            if(success > 0)
            {
                return object;
            }
        } catch (SQLException throwables) {
            System.err.println("");
        }
        return null;
    }
}
