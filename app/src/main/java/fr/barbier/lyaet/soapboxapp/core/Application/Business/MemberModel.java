package fr.barbier.lyaet.soapboxapp.core.Application.Business;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import fr.barbier.lyaet.soapboxapp.core.domain.model.Member;
import fr.barbier.lyaet.soapboxapp.core.domain.model.Team;
import org.jetbrains.annotations.NotNull;

@DatabaseTable(tableName = "member")
public class MemberModel extends BasicModel implements Member {

    @DatabaseField
    private String lastName;

    @DatabaseField
    private String name;

    @DatabaseField(canBeNull = false, foreign = true)
    private TeamModel team;

    public static @NotNull MemberModel create(String firstName, String lastName) {
        MemberModel output = new MemberModel();
        output.name = firstName;
        output.lastName = lastName;
        return output;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Team getTeam() {
        return this.team;
    }

    public void setTeam(TeamModel team) {
        this.team = team;
    }
}
