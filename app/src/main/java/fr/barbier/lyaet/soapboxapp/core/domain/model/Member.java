package fr.barbier.lyaet.soapboxapp.core.domain.model;

public interface Member {

    long getId();

    String getName();

    String getLastName();

    Team getTeam();
}
