package fr.barbier.lyaet.soapboxapp.core.Application.repository;

import com.j256.ormlite.dao.Dao;

import fr.barbier.lyaet.soapboxapp.core.Application.Business.MemberModel;

public class MemberRepository extends Repository<MemberModel> {
    private static MemberRepository instance;

    public static MemberRepository get()
    {
        if(instance != null) {
            return instance;
        }
        throw new RuntimeException("MemberRepository has not been initialised yet");
    }

    public MemberRepository(Dao<MemberModel, Integer> dao) {
        super(dao);
        instance = this;
    }
}
