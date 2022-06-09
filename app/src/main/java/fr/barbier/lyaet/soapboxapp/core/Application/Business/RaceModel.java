package fr.barbierlyaet.Application.Business;

import fr.barbierlyaet.domain.Race;
import fr.barbierlyaet.domain.Team;

import java.util.Collection;
import java.util.Date;

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
