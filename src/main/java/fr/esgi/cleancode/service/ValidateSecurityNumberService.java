package fr.esgi.cleancode.service;

import static java.lang.Double.parseDouble;

public class ValidateSecurityNumberService {
    public boolean driverSocialSecurityNumberIsNull(String driverSocialSecurityNumber){
        return driverSocialSecurityNumber == null;
    }

    public boolean driverSocialSecurityNumberNotFormat(String driverSocialSecurityNumber){
        return Double.isNaN(parseDouble(driverSocialSecurityNumber));
    }

    public boolean driverSocialSecurityNumberLenghtIs15(String driverSocialSecurityNumber){
        return driverSocialSecurityNumber.length() == 15;
    }
}
