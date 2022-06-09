package fr.barbier.lyaet.soapboxapp.core.Application.Business;

import java.util.Collection;

import fr.barbier.lyaet.soapboxapp.core.domain.Grade;
import fr.barbier.lyaet.soapboxapp.core.domain.Participation;
import fr.barbier.lyaet.soapboxapp.core.domain.Race;
import fr.barbier.lyaet.soapboxapp.core.domain.Team;

public class ParticipationModel extends BasicModel implements Participation {

    private Race race;

    private Team team;

    private Collection<Grade> grades;

    private String time;

    private String soapBoxPicturePath;

    @Override
    public Race getRace() {
        return race;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public Collection<Grade> getGrades() {
        return grades;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public String getSoapBoxPicturePath() {
        return soapBoxPicturePath;
    }

    @Override
    public double computeFinalScore() {
        return 0;
    }
}
