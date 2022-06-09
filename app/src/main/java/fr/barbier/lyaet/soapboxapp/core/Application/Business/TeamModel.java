package fr.barbier.lyaet.soapboxapp.core.Application.Business;

import fr.barbier.lyaet.soapboxapp.core.domain.Member;
import fr.barbier.lyaet.soapboxapp.core.domain.Team;

import java.util.Collection;

public class TeamModel extends BasicModel implements Team {

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
