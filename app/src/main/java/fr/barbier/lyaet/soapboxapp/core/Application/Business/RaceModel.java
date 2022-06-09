package fr.barbier.lyaet.soapboxapp.core.Application.Business;

import java.util.Collection;
import java.util.Date;

import fr.barbier.lyaet.soapboxapp.core.domain.Race;
import fr.barbier.lyaet.soapboxapp.core.domain.Team;

public class RaceModel extends BasicModel implements Race {

    private String name;

    private Collection<Team> teams;

    private Date date;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<Team> getTeams() {
        return teams;
    }

    @Override
    public Date getDate() {
        return date;
    }
}
