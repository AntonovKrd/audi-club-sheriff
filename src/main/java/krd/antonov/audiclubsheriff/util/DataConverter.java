package krd.antonov.audiclubsheriff.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataConverter {

    private static final String dateBirthPattern = "dd.MM.yyyy";

    public static String reformatPhone(String phone) {
        return (phone.startsWith("7")) ? phone.replaceFirst("7", "8") : phone;
    }

    public static LocalDate convertStringToLocalDate(String dateBirth) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateBirthPattern, Locale.ENGLISH);
        return LocalDate.parse(dateBirth, dtf);
    }
}
