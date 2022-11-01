package org.example.lineNumbersParser;

public enum LineStatus {
    ORDINARY("Ordinary","Line with ordinary status","linia"),
    CHANGING_TIMETABLE("Change timetable","Line with change timetable","liniaZ"),
    TEMPORARY_CHANGE_TIMETABLE("Temporary change timetable","Line with temporary change timetable","linia0"),
    NIGHT("Night","Night Line","liniaN");


    LineStatus(String name,String description,String classCss){
          this.nameStatus=name;
          this.descriptionStatus=description;
          this.classCssOnWebMpk=classCss;

    }


    public String getNameStatus() {
        return nameStatus;
    }

    public String getClassCssOnWebMpk() {
        return classCssOnWebMpk;
    }

    public String getDescriptionStatus() {
        return descriptionStatus;
    }

    private String nameStatus;
    private String classCssOnWebMpk;
    private String descriptionStatus;
}
