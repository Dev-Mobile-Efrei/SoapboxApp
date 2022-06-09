package fr.barbier.lyaet.soapboxapp.core.domain;

import java.util.Collection;
import java.util.Date;

public interface Race {
    long getId();
    String getName();
    Collection<Team> getTeams();
    Date getDate();
}
