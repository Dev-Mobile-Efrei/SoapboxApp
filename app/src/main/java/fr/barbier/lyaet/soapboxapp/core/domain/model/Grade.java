package fr.barbier.lyaet.soapboxapp.core.domain.model;

public interface Grade {
    long getId();
    String getJury();
    int getValue();
    Participation getParticipation();
}
