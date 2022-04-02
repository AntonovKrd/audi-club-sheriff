package krd.antonov.audiclubsheriff.util;

import org.apache.commons.validator.GenericValidator;

import java.util.regex.Pattern;

public class TempDataChecker {

    private static final Pattern namePattern = Pattern.compile("^[\\p{L} .'-]{2,255}$");

    public static String reformatPhone(String phone) {
        return (phone.startsWith("7")) ? phone.replaceFirst("7", "8") : phone;
    }

    public static boolean isNameCorrect(String name){
        return namePattern.matcher(name).find();
    }

    public static boolean isDateBirthCorrect(String date){
       return GenericValidator.isDate(date,"dd.MM.yyyy", true);
    }

    public static boolean isYearCorrect(String year){
        return GenericValidator.isDate(year, "yyyy", true);
    }
}
