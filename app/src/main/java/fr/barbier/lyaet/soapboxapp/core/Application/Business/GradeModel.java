package fr.barbier.lyaet.soapboxapp.core.Application.Business;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.barbier.lyaet.soapboxapp.core.domain.Grade;

@DatabaseTable(tableName = "grade")
public class GradeModel extends BasicModel implements Grade {

    @DatabaseField
    private String jury;

    @DatabaseField
    private int value;

    @Override
    public String getJury() {
        return this.jury;
    }

    @Override
    public int getValue() {
        return this.value;
    }
}
