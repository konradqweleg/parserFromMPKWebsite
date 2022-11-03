package org.example.lineNumbersParser;

import java.util.regex.Pattern;

public class Utility {
    public static final String REGEX_NUMBER="(?<=\\\\D)(?=\\\\d)";
    public static final String REGEX_SPACE =" ";

    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }
}
