package fr.barbier.lyaet.soapboxapp.core.Application.Business;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import fr.barbier.lyaet.soapboxapp.core.domain.model.Participation;
import fr.barbier.lyaet.soapboxapp.core.domain.model.Race;

@DatabaseTable(tableName = "race")
public class RaceModel extends BasicModel implements Race {

    @DatabaseField()
    private String name;

    @DatabaseField()
    private Date date;

    @ForeignCollectionField(orderColumnName = "race")
    private ForeignCollection<ParticipationModel> participations;

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

    public static RaceModel create(String name)
    {
        RaceModel raceModel = new RaceModel();
        raceModel.name = name;
        raceModel.date = Date.from(Instant.now());
        return raceModel;
    }
}
