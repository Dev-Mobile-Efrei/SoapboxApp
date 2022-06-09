package fr.barbier.lyaet.soapboxapp.core.Application.Business;

import fr.barbier.lyaet.soapboxapp.core.domain.Grade;

public class GradeModel extends BasicModel implements Grade {
    private int id;

    private String jury;

    private int value;

    @Override
    public String getJury() {
        return this.jury;
    }

    @Override
    public int getValue() {
        return this.value;
    }
}
