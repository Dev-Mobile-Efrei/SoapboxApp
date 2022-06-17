package fr.barbier.lyaet.soapboxapp.core.Application.Business;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import fr.barbier.lyaet.soapboxapp.core.domain.model.Participation;
import fr.barbier.lyaet.soapboxapp.core.domain.model.Race;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@DatabaseTable(tableName = "race")
public class RaceModel extends BasicModel implements Race {

    @DatabaseField
    private String address;

    @DatabaseField
    private Date date;

    @DatabaseField
    private String name;

    @ForeignCollectionField(orderColumnName = "race")
    private ForeignCollection<ParticipationModel> participations;

    public static RaceModel create(String name) {
        RaceModel raceModel = new RaceModel();
        raceModel.name = name;
        raceModel.date = Date.from(Instant.now());
        return raceModel;
    }

    public static RaceModel create(String name, Date date, String address) {
        RaceModel raceModel = new RaceModel();
        raceModel.name = name;
        raceModel.date = date;
        raceModel.address = address;
        return raceModel;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<Participation> getParticipations() {
        return Collections.unmodifiableCollection(this.participations);
    }
}
