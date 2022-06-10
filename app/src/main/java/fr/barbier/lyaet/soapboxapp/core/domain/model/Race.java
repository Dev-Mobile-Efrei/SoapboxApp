package fr.barbier.lyaet.soapboxapp.core.domain.model;

import java.util.Collection;
import java.util.Date;

public interface Race {
    long getId();
    String getName();
    Collection<Participation> getParticipations();
    Date getDate();
}
