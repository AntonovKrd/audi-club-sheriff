package krd.antonov.audiclubsheriff.util;

import org.apache.commons.validator.GenericValidator;
import org.apache.http.client.utils.DateUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.regex.Pattern;

public class TempDataChecker {

    private static final Pattern namePattern = Pattern.compile("^[\\p{L} .'-]{2,255}$");
    private static final Pattern licensePlatePattern = Pattern.compile("^[A-Z0-9]{8,9}$");
    private static final String dateBirthPattern = "dd.MM.yyyy";
    private static final String yearPattern = "yyyy";
    private static final DateTimeFormatter yearFormat = DateTimeFormat.forPattern(yearPattern);

    public static String reformatPhone(String phone) {
        return (phone.startsWith("7")) ? phone.replaceFirst("7", "8") : phone;
    }

    public static boolean isNameCorrect(String name) {
        return namePattern.matcher(name).find();
    }

    public static boolean isDateBirthCorrect(String date) {
        return (GenericValidator.isDate(date, dateBirthPattern, true)
                && DateUtils.parseDate(date, new String[]{dateBirthPattern})
                .before(LocalDate.now().minusYears(18).toDate()));
    }

    public static boolean isYearCorrect(String year) {
        return (GenericValidator.isDate(year, yearPattern, true)
                && DateUtils.parseDate(year, new String[]{yearPattern})
                .before(LocalDate.parse(year, yearFormat).plusYears(1).toDate()));
    }

    public static boolean isLicensePlateCorrect(String licensePlate) {
        return licensePlatePattern.matcher(licensePlate).find();
    }
}
