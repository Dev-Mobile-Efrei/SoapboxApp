package fr.barbier.lyaet.soapboxapp.core.Application.repository;

import com.j256.ormlite.dao.Dao;

import fr.barbier.lyaet.soapboxapp.core.Application.Business.MemberModel;

public class MemberRepository extends Repository<MemberModel> {
    public static MemberRepository instance;

    public MemberRepository(Dao<MemberModel, Integer> dao) {
        super(dao);
        instance = this;
    }
}
