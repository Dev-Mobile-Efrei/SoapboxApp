package fr.barbier.lyaet.soapboxapp;

import fr.barbier.lyaet.soapboxapp.core.domain.utils.Logger;

public class ConsoleLogger implements Logger {

    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
