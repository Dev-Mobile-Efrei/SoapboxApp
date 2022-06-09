package fr.barbierlyaet.Application.Business;

import fr.barbierlyaet.domain.Grade;
import fr.barbierlyaet.domain.Member;
import fr.barbierlyaet.domain.Team;

import java.util.Collection;

public class TeamModel extends BasicModel implements Team{

    private String name;

    private Collection<Member> members;

    private String nationality;


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<Member> getMembers() {
        return this.members;
    }

    @Override
    public String getNationality() {
        return this.nationality;
    }
}
