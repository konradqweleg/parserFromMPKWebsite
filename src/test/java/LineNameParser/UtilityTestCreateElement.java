package LineNameParser;

import org.example.lineNumbersParser.LineStatus;
import org.example.lineNumbersParser.UriUtility;
import org.example.lineNumbersParser.UtilityCSSHTML;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;


public  class UtilityTestCreateElement {
    public static Element createInvalidFormatLineNumberElement(){
      return   new Element(Tag.valueOf(UtilityCSSHTML.HTML_LIST_NUMERIC_ELEM), UriUtility.BASIC_EMPTY_URI);
    }

    public static Element createValidFormatLineNumberElement(String lineNumber){
        Element correctFormatElemLineData = new Element(Tag.valueOf(UtilityCSSHTML.HTML_HREF_ELEM), UriUtility.BASIC_EMPTY_URI);
        correctFormatElemLineData.append(lineNumber);
        return correctFormatElemLineData;
    }

    public static Elements createElementsIsInvalidLineNumberFormat(){
        Elements elements=new Elements(){{
            add( UtilityTestCreateElement.createInvalidFormatLineNumberElement());
            add( UtilityTestCreateElement.createInvalidFormatLineNumberElement());
            add( UtilityTestCreateElement.createInvalidFormatLineNumberElement());
        }};
        return elements;
    }


    public static Elements createElementsValidLineNumber(String lineNumber){
        Elements elements=new Elements(){{
            add( UtilityTestCreateElement.createValidFormatLineNumberElement(lineNumber));
            add( UtilityTestCreateElement.createValidFormatLineNumberElement(lineNumber));
            add( UtilityTestCreateElement.createValidFormatLineNumberElement(lineNumber));
        }};
        return elements;
    }

    public static Document createDocumentInvalidNoLineNumberElem(){
        Document document=new Document(UriUtility.BASIC_EMPTY_URI);
        for(Element elem:createElementsIsInvalidLineNumberFormat()){
            document.appendElement(elem.tagName());
        }
        return document;

    }


    public static Document createDocumentLineNumberElem(String lineNumber){
        Document document=new Document(UriUtility.BASIC_EMPTY_URI);
        for(Element elem:createElementsValidLineNumber(lineNumber)){
            document.appendElement(elem.tagName()).text(elem.text()).addClass(LineStatus.CHANGING_TIMETABLE.getClassCssOnWebMpk()
            ).attr("href", "linkExample");

        }
        return document;

    }



}
