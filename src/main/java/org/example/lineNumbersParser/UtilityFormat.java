package org.example.lineNumbersParser;

public class UtilityFormat {
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static String removeAllCharacterDiffrendToNumber(String numberRawWithText){
        String[] polishCharacters={"ż","ź","ł","ą","ł","ń","ę"};
        String potentialVehicleStopNumber=numberRawWithText.replaceAll(Utility.REGEX_VISIBLE_CHAR, Utility.REGEX_NO_CHAR);
        for(String polishChar:polishCharacters){
            potentialVehicleStopNumber=potentialVehicleStopNumber.replaceAll(polishChar,Utility.REGEX_NO_CHAR);
        }
        potentialVehicleStopNumber=potentialVehicleStopNumber.trim();
        return potentialVehicleStopNumber;
    }
}
