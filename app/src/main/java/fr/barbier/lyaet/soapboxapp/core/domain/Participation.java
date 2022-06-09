package fr.barbierlyaet.domain;

import java.util.Collection;

public interface Participation {
    long getId();
    Race getRace();
    Team getTeam();
    Collection<Grade> getGrades();
    String getTime();
    String getSoapBoxPicturePath();
    double computeFinalScore();

}
