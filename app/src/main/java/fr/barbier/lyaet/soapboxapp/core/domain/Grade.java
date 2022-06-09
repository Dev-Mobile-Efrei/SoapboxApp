package fr.barbier.lyaet.soapboxapp.core.domain;

public interface Grade {
    long getId();
    String getJury();
    int getValue();
    Participation getParticipation();
}
