package fr.barbier.lyaet.soapboxapp.core.domain.model;

import java.util.Collection;

public interface Team {
    long getId();
    String getName();
    Collection<Member> getMembers();
    String getNationality();

}
