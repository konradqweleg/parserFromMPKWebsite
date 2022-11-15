package org.example.lineNumbersParser;

import java.util.regex.Pattern;

public class Utility {
    public static final String REGEX_NUMBER="(?<=\\\\D)(?=\\\\d)";
    public static final String REGEX_SPACE =" ";

    public static final String REGEX_NO_CHAR ="";
    public static final String REGEX_NUMBER2="\\d";

    public static final String REGEX_IS_TEXT_CONTAINS_NUMBER=".*\\d.*";

    public static final String REGEX_VISIBLE_CHAR="[*a-zA-Z]";

    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }
}
