package fr.barbier.lyaet.soapboxapp.core.Application.Business;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import fr.barbier.lyaet.soapboxapp.core.domain.Race;
import fr.barbier.lyaet.soapboxapp.core.domain.Team;

@DatabaseTable(tableName = "race")
public class RaceModel extends BasicModel implements Race {

    @DatabaseField()
    private String name;

    @ForeignCollectionField(eager = true, orderColumnName = "race")
    private ForeignCollection<Team> teams;

    @DatabaseField()
    private Date date;

    public RaceModel(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<Team> getTeams() {
        return Collections.unmodifiableCollection(this.teams);
    }

    @Override
    public Date getDate() {
        return date;
    }
}
