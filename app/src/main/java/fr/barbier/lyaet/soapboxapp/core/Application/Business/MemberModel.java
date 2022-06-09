package fr.barbier.lyaet.soapboxapp.core.Application.Business;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.barbier.lyaet.soapboxapp.core.domain.Member;
import fr.barbier.lyaet.soapboxapp.core.domain.Team;

@DatabaseTable(tableName = "member")
public class MemberModel extends BasicModel implements Member {

    @DatabaseField
    private String name;

    @DatabaseField
    private String lastName;

    @DatabaseField(canBeNull = false, foreign = true)
    private Team team;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public Team getTeam() {
        return this.team;
    }
}
