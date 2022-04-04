package krd.antonov.audiclubsheriff.util;

import org.apache.commons.validator.GenericValidator;
import org.apache.http.client.utils.DateUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.regex.Pattern;

public class TempDataChecker {

    private static final Pattern namePattern = Pattern.compile("^[А-Яа-я .'-]{2,255}$");
    private static final String dateBirthPattern = "dd.MM.yyyy";
    private static final Pattern cityPattern = Pattern.compile("^[А-Яа-я]+(?:[\\s-][А-Яа-я]+)*$");
    private static final Pattern licensePlatePattern = Pattern.compile("^[АВЕКМНОРСТУХавекмнорстухA-Za-z0-9]{8,9}$");
    private static final Pattern vehicleModelPattern = Pattern.compile("^[A-Za-z0-9 ]{2,25}$");
    private static final String yearPattern = "yyyy";
    private static final DateTimeFormatter yearFormat = DateTimeFormat.forPattern(yearPattern);

    public static boolean isNameCorrect(String name) {
        return namePattern.matcher(name).find();
    }

    public static boolean isDateBirthCorrect(String date) {
        return (GenericValidator.isDate(date, dateBirthPattern, true)
                && DateUtils.parseDate(date, new String[]{dateBirthPattern})
                .before(LocalDate.now().minusYears(18).toDate())
                && DateUtils.parseDate(date, new String[]{dateBirthPattern})
                .after(LocalDate.now().minusYears(80).toDate()));
    }

    public static boolean isCityCorrect(String city) {
        return cityPattern.matcher(city).find();
    }

    public static boolean isYearCorrect(String year) {
        return (GenericValidator.isDate(year, yearPattern, true)
                && DateUtils.parseDate(year, new String[]{yearPattern})
                .before(LocalDate.parse(String.valueOf(LocalDate.now().getYear()), yearFormat).plusYears(1).toDate())
                && DateUtils.parseDate(year, new String[]{yearPattern})
                .after(LocalDate.parse(String.valueOf(LocalDate.now().getYear()), yearFormat).minusYears(60).toDate()));
    }

    public static boolean isLicensePlateCorrect(String licensePlate) {
        return licensePlatePattern.matcher(licensePlate).find();
    }

    public static boolean isVehicleModelCorrect(String vehicleModel) {
        return vehicleModelPattern.matcher(vehicleModel).find();
    }
}
