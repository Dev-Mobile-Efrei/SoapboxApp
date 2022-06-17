package fr.barbier.lyaet.soapboxapp.core.Application.Business;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import fr.barbier.lyaet.soapboxapp.core.domain.model.Team;
import org.jetbrains.annotations.NotNull;

@DatabaseTable(tableName = "team")
public class TeamModel extends BasicModel implements Team {

    @ForeignCollectionField(orderColumnName = "team")
    private ForeignCollection<MemberModel> members;

    @DatabaseField
    private String name;

    @DatabaseField
    private String nationality;

    public static @NotNull TeamModel create(String name, String nationality) {
        TeamModel output = new TeamModel();
        output.name = name;
        output.nationality = nationality;
        return output;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getNationality() {
        return this.nationality;
    }
}
