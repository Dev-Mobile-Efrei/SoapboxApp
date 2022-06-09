package fr.barbier.lyaet.soapboxapp.core.Application.Business;

import fr.barbier.lyaet.soapboxapp.core.domain.Member;

public class MemberModel extends BasicModel implements Member {

    private String name;

    private String lastName;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

}
