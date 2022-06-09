package fr.barbier.lyaet.soapboxapp.core.Application.Business;

import java.util.Date;

public abstract class BasicModel {

    private long id;

    private Date createdAt;

    public long getId()
    {
        return this.id;
    }
    public Date getCreatedAt()
    {
        return createdAt;
    }

}
