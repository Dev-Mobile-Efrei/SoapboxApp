package fr.barbierlyaet.domain;

import java.util.Collection;

public interface Team {
    long getId();
    String getName();
    Collection<Member> getMembers();
    String getNationality();

}
