package krd.antonov.audiclubsheriff.util;

public class FormatDataHelper {

    public static String reformatPhone(String phone) {
        return (phone.startsWith("7")) ? phone.replaceFirst("7", "8") : phone;
    }
}
