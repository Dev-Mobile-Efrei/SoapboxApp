package fr.barbier.lyaet.soapboxapp.core.Application.Business;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import fr.barbier.lyaet.soapboxapp.core.domain.Member;
import fr.barbier.lyaet.soapboxapp.core.domain.Team;

import java.util.Collection;
import java.util.Collections;

@DatabaseTable(tableName = "team")
public class TeamModel extends BasicModel implements Team {

    @DatabaseField()
    private String name;

    @ForeignCollectionField(orderColumnName = "team")
    private ForeignCollection<MemberModel> members;

    @DatabaseField
    private String nationality;


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<Member> getMembers() {
        return Collections.unmodifiableCollection(this.members);
    }

    @Override
    public String getNationality() {
        return this.nationality;
    }
}
