package fr.barbier.lyaet.soapboxapp.core.Application.Business;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import fr.barbier.lyaet.soapboxapp.core.domain.Participation;
import fr.barbier.lyaet.soapboxapp.core.domain.Race;

@DatabaseTable(tableName = "race")
public class RaceModel extends BasicModel implements Race {

    @DatabaseField()
    private String name;

    @DatabaseField()
    private Date date;

    @ForeignCollectionField(orderColumnName = "race")
    private ForeignCollection<Participation> participations;

    public RaceModel(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<Participation> getParticipations() {
        return Collections.unmodifiableCollection(this.participations);
    }

    @Override
    public Date getDate() {
        return date;
    }
}
