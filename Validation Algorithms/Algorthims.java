package src.codeforces;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Algorthims {
    public static void main(String[] args) throws Exception {

    }

    /**
     * method to validate an integer value based on min and max limit
     *
     * @param value    - the value to validate
     * @param minLimit - the minimum possible value, default value will be 0
     * @param maxLimit - the maximum possible value, default value will be 2 raise to power 31
     * @return
     */
    private static boolean isIntegerValid(Integer value, Integer minLimit, Integer maxLimit) {
        //Base Case
        if (value == null) return false;
        if (minLimit == null) minLimit = 1;
        if (maxLimit == null) maxLimit = Integer.MAX_VALUE;
        return value.compareTo(minLimit) > -1 && value.compareTo(maxLimit) <= 0;
    }

    /**
     * method to validate a long value based on min and max limit
     *
     * @param value    - the value to validate
     * @param minLimit - the minimum possible value, default value will be 0
     * @param maxLimit - the maximum possible value, default value will be 2 raise to power 64
     * @return
     */
    private static boolean isLongValid(Long value, Long minLimit, Long maxLimit) {
        //Base Case
        if (value == null) return false;
        if (minLimit == null) minLimit = 1L;
        if (maxLimit == null) maxLimit = Long.MAX_VALUE;
        return value.compareTo(minLimit) > -1 && value.compareTo(maxLimit) <= 0;
    }

    /**
     * method to validate whether a string value is null or empty,
     * even after removing leading and trailing whitespaces spaces
     *
     * @param str - the string value
     * @return
     */
    public boolean isStringValid(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * method to check whether a string value contains alphabets in it
     *
     * @param str - the string value to perform checking on
     * @return
     */
    private boolean containsAlphabets(String str) {
        if (str == null || str.trim().isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            int asciiValue = (int) str.charAt(i);
            if ((asciiValue >= 65 && asciiValue <= 90)
                    || (asciiValue >= 97 && asciiValue <= 122)) return true;
        }
        return false;
    }

    /**
     * method to check whether a string value contains digits in it
     *
     * @param str - the string value to perform check on
     * @return
     */
    private boolean containsDigits(String str) {
        if (str == null || str.trim().isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            int asciiValue = (int) str.charAt(i);
            if (asciiValue >= 48 && asciiValue <= 57) return true;
        }
        return false;
    }

    /**
     * method to check whether a string contains only numeric digits in it or not
     *
     * @param str - the string value to perform checking on
     * @return
     */
    private boolean containsAlphabetsOrSpecialCharacters(String str) {
        if (str == null || str.trim().isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            int asciiValue = (int) str.charAt(i);
            if (asciiValue < 48 || asciiValue > 57) return true;
        }
        return false;
    }

    /**
     * method to check whether a string value contains special characters in it
     *
     * @param str - the string value to perform check on
     * @return
     */
    private boolean containsSpecialCharacters(String str) {
        if (str == null || str.trim().isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            int asciiValue = (int) str.charAt(i);
            if ((asciiValue >= 32 && asciiValue <= 47) || (asciiValue >= 58 && asciiValue <= 64)
                    || (asciiValue >= 91 && asciiValue <= 96) || (asciiValue >= 123 && asciiValue <= 127)) {
                return true;
            }
        }
        return false;
    }

    /**
     * method to check whether a string contains anything other than alphabets in it
     *
     * @param str - a string value
     * @return
     */
    public boolean containsDigitsOrSpecialCharacters(String str) {
        if (str == null || str.trim().isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            int asciiValue = (int) str.charAt(i);
            if ((asciiValue >= 65 && asciiValue <= 90) || (asciiValue >= 97 && asciiValue <= 122)) continue;
            return true;
        }
        return false;
    }

    /**
     * method to check whether an email address is valid or not
     *
     * @param emailAddress - the string value as email address
     * @return - returns true if the emailAddress is valid otherwise false
     */
    public static boolean isValidEmailAddress(String emailAddress) {
        //Base Case
        if (emailAddress == null) return false;
        emailAddress = emailAddress.trim();
        if (emailAddress.length() == 0 || emailAddress.equals("")) return false;
        if (!emailAddress.contains("@") || emailAddress.length() > 320 || emailAddress.contains("..") || (emailAddress.length() > 0 && emailAddress.charAt(0) == '@') || (emailAddress.length() >= 2 && emailAddress.contains("@.")) || (emailAddress.length() > 0 && emailAddress.endsWith(".")))
            return false;
        int firstIndexOfAtSymbol = emailAddress.indexOf("@");
        int lastIndexOfAtSymbol = emailAddress.lastIndexOf("@");
        /**
         * A valid email address only allows 64 octets (characters) before @ symbol, and 255 octets after @ symbol.
         * If the value doesn't meet the above requirement, it ain't a valid email address
         */
        if (firstIndexOfAtSymbol > 64 || firstIndexOfAtSymbol != lastIndexOfAtSymbol) return false;
        for (int i = 0; i < firstIndexOfAtSymbol; i++) {
            char currentChar = emailAddress.charAt(i);
            if (currentChar == '.' || currentChar == '-' || currentChar == '_') {
                if ((i + 1) >= emailAddress.length()) return false;
                int asciiValue = (int) emailAddress.charAt(i + 1);
                if ((asciiValue >= 48 && asciiValue <= 57) || (asciiValue >= 65 && asciiValue <= 90) || (asciiValue >= 97 && asciiValue <= 122))
                    continue;
                return false;
            }
        }
        return true;
    }
}