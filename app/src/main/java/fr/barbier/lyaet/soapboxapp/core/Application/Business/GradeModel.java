package fr.barbier.lyaet.soapboxapp.core.Application.Business;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.barbier.lyaet.soapboxapp.core.domain.model.Grade;
import fr.barbier.lyaet.soapboxapp.core.domain.model.Participation;

@DatabaseTable(tableName = "grade")
public class GradeModel extends BasicModel implements Grade {

    @DatabaseField
    private String jury;

    @DatabaseField
    private int value;

    @DatabaseField(canBeNull = false, foreign = true)
    private ParticipationModel participation;

    @Override
    public String getJury() {
        return this.jury;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public Participation getParticipation() {
        return this.participation;
    }
}
