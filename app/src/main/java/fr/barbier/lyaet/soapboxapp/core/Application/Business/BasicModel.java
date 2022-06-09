package fr.barbier.lyaet.soapboxapp.core.Application.Business;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

public abstract class BasicModel {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private Date createdAt;

    public long getId()
    {
        return this.id;
    }
    public Date getCreatedAt()
    {
        return this.createdAt;
    }

}
