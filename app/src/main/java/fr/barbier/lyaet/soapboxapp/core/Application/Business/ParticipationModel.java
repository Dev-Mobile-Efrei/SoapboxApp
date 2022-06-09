package fr.barbier.lyaet.soapboxapp.core.Application.Business;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

import fr.barbier.lyaet.soapboxapp.core.domain.Grade;
import fr.barbier.lyaet.soapboxapp.core.domain.Participation;
import fr.barbier.lyaet.soapboxapp.core.domain.Race;
import fr.barbier.lyaet.soapboxapp.core.domain.Team;

@DatabaseTable(tableName = "participation")
public class ParticipationModel extends BasicModel implements Participation {

    @DatabaseField(canBeNull = false, foreign = true)
    private Race race;

    @DatabaseField(canBeNull = false, foreign = true)
    private Team team;

    @ForeignCollectionField(eager = true, orderColumnName = "participation")
    private Collection<Grade> grades;

    @DatabaseField
    private String time;

    @DatabaseField
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
