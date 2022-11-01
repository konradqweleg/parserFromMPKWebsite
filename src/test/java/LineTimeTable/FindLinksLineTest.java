package LineTimeTable;

import LineNameParser.UtilityTestCreateElement;
import org.example.lineNumbersParser.LineData;
import org.example.lineNumbersParser.LineStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parserVehicleStop.FindLinksLine;
import parserVehicleStop.LinkLineTimeTable;


import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.List;
import java.util.Optional;

public class FindLinksLineTest {
    private static final String  exampleLineNumberWithDocumentCreated="137";

    private FindLinksLine findLinksLine;

    @Before
    public void createObjectFindLinksLine() {
        findLinksLine=new FindLinksLine();
    }


    @Test
    public void findsLinkForLine_noExsistLine(){
        int noExistLineNumber=1233;

        LineData testNoExistLine=new LineData(noExistLineNumber, LineStatus.ORDINARY);
        Optional<LinkLineTimeTable> lineNoExsist=findLinksLine.findLinkToTimetableForNumberLine(testNoExistLine,
                UtilityTestCreateElement.createDocumentLineNumberElem(exampleLineNumberWithDocumentCreated));

        Assert.assertFalse(lineNoExsist.isPresent());


    }


    @Test
    public void findsLinkForLine_existsLine(){
        int noExistLineNumber=137;

        LineData existLine=new LineData(noExistLineNumber, LineStatus.ORDINARY);
        Optional<LinkLineTimeTable> lineExists=findLinksLine.findLinkToTimetableForNumberLine(existLine,
                UtilityTestCreateElement.createDocumentLineNumberElem(exampleLineNumberWithDocumentCreated));

        Assert.assertTrue(lineExists.isPresent());


    }


    @org.junit.Test(expected = NullPointerException.class)
    public void findsLinkForLine_nullParam(){
        findLinksLine.findLinkToTimetableForNumberLine(null,null);
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void findLinkAllLines_nullParam() throws IOException {
        findLinksLine.findAllLinkToTimeTableForAllLines(null);
    }

    @Test
    public void findLinkAllLines_addCorrectPage() throws IOException {
        int LINE_NUMBER_MUST_GREATER=0;
        int LINE_NUMBER_MUST_DOWN=1000;

        List<LinkLineTimeTable> listLink=findLinksLine.findAllLinkToTimeTableForAllLines(
                UtilityTestCreateElement.createDocumentLineNumberElem(exampleLineNumberWithDocumentCreated));

        for(LinkLineTimeTable link:listLink) {
            Assert.assertTrue(link.getLineData().getNumberLine() > LINE_NUMBER_MUST_GREATER
                    && link.getLineData().getNumberLine() < LINE_NUMBER_MUST_DOWN);

            Assert.assertTrue(link.getLink().length()>LINE_NUMBER_MUST_GREATER);
        }

    }



    @org.junit.Test(expected = UnexpectedException.class)
    public void findLinkAllLines_addInvalidPage() throws IOException {
        findLinksLine.findAllLinkToTimeTableForAllLines(UtilityTestCreateElement.createDocumentInvalidNoLineNumberElem());

    }


}
