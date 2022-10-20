package test;

import main.LineData;
import main.LineStatus;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import main.LineNameParser;
import org.testng.annotations.Test;;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class LineNameParserTest {

    private LineNameParser lineNameParser;

    @Before
    public void createObjectLineNameParser() {
        lineNameParser=new LineNameParser();
    }


    @org.junit.Test
    public  void createLineDataFromElement_giveNullParam(){
        Optional<LineData> lineData= lineNameParser.createLineDataFromElement(null,null);
        Assert.assertFalse(lineData.isPresent());

    }

    @Test
    public void createLineDataFromElement_giveIncorrectFormatElem(){
        Element incorrectFormatElem = UtilityTestCreateElement.createInvalidFormatLineNumberElement();
        LineStatus exampleStatus= LineStatus.CHANGING_TIMETABLE;
        Optional<LineData> lineData=lineNameParser.createLineDataFromElement(incorrectFormatElem,exampleStatus);
        Assert.assertFalse(lineData.isPresent());
    }

    @org.junit.Test
    public void createLineDataFromElement_giveCorrectData(){
        String numberLineString="137";
        int numberLineInteger=137;

        LineStatus exampleStatus= LineStatus.CHANGING_TIMETABLE;
        Element correctFormatElemLineData = UtilityTestCreateElement.createValidFormatLineNumberElement(numberLineString);
        Optional<LineData> lineData=lineNameParser.createLineDataFromElement(correctFormatElemLineData,exampleStatus);

        Assert.assertTrue(lineData.isPresent());
        Assert.assertEquals(numberLineInteger,lineData.get().getNumberLine());
        Assert.assertEquals(LineStatus.CHANGING_TIMETABLE,lineData.get().getStatusLine());


    }



    @org.junit.Test(expected = NullPointerException.class)
    public void createListLineFromElements_giveNullValueAsParam(){
        lineNameParser.createListLineFromElements(null,null);

    }

    @org.junit.Test
    public void createListLineFromElements_giveAllIncorrectElem(){
      int NO_ELEM=0;
      LineStatus exampleStatus= LineStatus.CHANGING_TIMETABLE;
      ArrayList<LineData> lineData= lineNameParser.createListLineFromElements(UtilityTestCreateElement.createElementsIsInvalidLineNumberFormat(),exampleStatus);
      Assert.assertEquals(NO_ELEM,lineData.size());

    }

    @org.junit.Test
    public void createListLineFromElements_giveCorrectNumberLineElements(){
        int FIRST_INDEX=0;
        String lineNumberExampleString="137";
        int lineNumberExampleInteger=137;
        Elements validLineNumberElements=UtilityTestCreateElement.createElementsValidLineNumber(lineNumberExampleString);

        LineStatus exampleStatus= LineStatus.CHANGING_TIMETABLE;
        ArrayList<LineData> lineData= lineNameParser.createListLineFromElements(validLineNumberElements,exampleStatus);
        Assert.assertEquals(validLineNumberElements.size(),lineData.size());

        LineData firstLine=lineData.get(FIRST_INDEX);

        Assert.assertEquals(lineNumberExampleInteger,firstLine.getNumberLine());
        Assert.assertEquals(exampleStatus,firstLine.getStatusLine());


    }


    @org.junit.Test(expected = NullPointerException.class)
    public void findLineWithSpecificStatusOnPage_giveNullParam(){
        lineNameParser.findLineWithSpecificStatusOnPage(null,null);

    }

    @org.junit.Test
    public void findLineWithSpecificStatusOnPage_addInvalidDocumentNoDataLine(){
      int NO_ELEM=0;
      LineStatus exampleStatus= LineStatus.CHANGING_TIMETABLE;
      ArrayList<LineData> lineData= lineNameParser.findLineWithSpecificStatusOnPage(exampleStatus,UtilityTestCreateElement.createDocumentInvalidNoLineNumberElem());
      Assert.assertEquals(NO_ELEM,lineData.size());
    }


    @org.junit.Test
    public void findLineWithSpecificStatusOnPage_addIValidDocumentLineData(){

        int FIRST_INDEX=0;
        String lineNumberExampleString="137";
        int lineNumberExampleInteger=137;
        LineStatus exampleStatus= LineStatus.CHANGING_TIMETABLE;
        ArrayList<LineData> lineData= lineNameParser.findLineWithSpecificStatusOnPage(exampleStatus,UtilityTestCreateElement.createDocumentLineNumberElem(lineNumberExampleString));
        Assert.assertEquals(3,lineData.size());

        LineData firstLine=lineData.get(FIRST_INDEX);

        Assert.assertEquals(lineNumberExampleInteger,firstLine.getNumberLine());
        Assert.assertEquals(exampleStatus,firstLine.getStatusLine());


    }

    @org.junit.Test
    public void parseLineNumberFromPage_exceptResultCorrectDataLines() throws IOException {
       ArrayList<LineData> lineData=  lineNameParser.parseLineNumberFromPage();
       boolean isValueGreatherThenZero= lineData.size() > 0;


       for (LineData lines:lineData) {
           boolean isNumberLineCorrect = (lines.getNumberLine() > 0) && (lines.getNumberLine() < 1000);
           Assert.assertTrue(isNumberLineCorrect);
       }
       Assert.assertTrue(isValueGreatherThenZero);

    }











}
