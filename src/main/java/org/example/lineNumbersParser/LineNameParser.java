package org.example.lineNumbersParser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class LineNameParser {


  public  Optional<LineData> createLineDataFromElement(Element elem, LineStatus status){

        if(elem==null || status ==null){
            return Optional.empty();
        }

        boolean isNameLineAreCorrectNumber=UtilityFormat.isNumeric(elem.text());

        if(isNameLineAreCorrectNumber){
           return  Optional.of(new LineData(Integer.parseInt(elem.text()),status));
        }
        return Optional.empty();

    }

    public  ArrayList<LineData> createListLineFromElements(Elements elements,LineStatus status){

        if(elements==null || status==null){
            throw new NullPointerException("Elements or Line Status Have to Not null");
        }

        ArrayList<LineData> data=new ArrayList<>();
        for (Element elem : elements) {
            Optional<LineData> lineData=createLineDataFromElement(elem,status);
            lineData.ifPresent(data::add);
        }
        return data;
    }


    public  ArrayList<LineData> findLineWithSpecificStatusOnPage(LineStatus lineStatus,Document page){

        if(lineStatus==null || page ==null){
            throw new NullPointerException("Line status or page must have not null");
        }

        ArrayList<LineData> lineWithSpecificStatus;
        String classCssSpecificTypeLine= UtilityCSSHTML.CLASS_MARK_IN_CSS+lineStatus.getClassCssOnWebMpk();
        Elements filterElemWithSpecificClassCss=page.select(classCssSpecificTypeLine);
        lineWithSpecificStatus=createListLineFromElements(filterElemWithSpecificClassCss,lineStatus);
        return lineWithSpecificStatus;
    }

    public  ArrayList<LineData> parseLineNumberFromPage(Document mainPage) throws IOException {
        ArrayList<LineData> lineNumber=new ArrayList<>();

        for (LineStatus line : LineStatus.values()) {
            lineNumber.addAll(findLineWithSpecificStatusOnPage(line,mainPage));
        }
        return lineNumber;
    }


    public static void main(String[]args) throws IOException {
        LineNameParser x=new LineNameParser();

        for (LineData a:x.parseLineNumberFromPage( DiskWebPageAccess.getMainPageDocument())) {
            System.out.println(a);
        }
    }
}
