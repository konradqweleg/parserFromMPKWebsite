package parserVehicleStop;

import org.example.lineNumbersParser.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class FindLinksLine {
    LineNameParser lineNameParser;


    public FindLinksLine(){
        lineNameParser=new LineNameParser();
    }
    public Optional<LinkLineTimeTable> findLinkToTimetableForNumberLine(LineData lineData, Document mainPage){
        String textChangeNumberToString="";

        if(lineData==null || mainPage==null){
            throw new NullPointerException("Line Data or Main Page have to no null");
        }

        Elements allLinkForAllLines=mainPage.select(UtilityCSSHTML.HTML_HREF_ELEM);
        for (Element linkForLine:allLinkForAllLines){
            if(linkForLine.text().trim().equals(textChangeNumberToString+lineData.getNumberLine())){
               if(linkForLine.hasAttr(UtilityCSSHTML.HREF_NAME)){
                   return Optional.of( new LinkLineTimeTable(linkForLine.attr(UtilityCSSHTML.HREF_NAME),lineData));
               }

            }
        }

        return Optional.empty();
    }


    public List<LinkLineTimeTable> findAllLinkToTimeTableForAllLines(Document mainPage) throws IOException {
        ArrayList<LineData> allLineNumber=lineNameParser.parseLineNumberFromPage(mainPage);
        if(allLineNumber==null || allLineNumber.size()==0){
            throw new UnexpectedException("No find Line");
        }

        ArrayList<LinkLineTimeTable> linkLineTimeTable=new ArrayList<>();

        for(LineData oneLineNumber : allLineNumber){
            Optional<LinkLineTimeTable> lineLinkToTimeTable=findLinkToTimetableForNumberLine(oneLineNumber,mainPage);
            lineLinkToTimeTable.ifPresent(linkLineTimeTable::add);
        }

        return linkLineTimeTable;

    }

    public static void main(String[]args ) throws IOException {
        FindLinksLine x=new FindLinksLine();
        Optional<LinkLineTimeTable> tb=x.findLinkToTimetableForNumberLine(new LineData(137,LineStatus.ORDINARY), DiskWebPageAccess.getMainPageDocument());
        System.out.println(tb.get().getLink());
    }

}
