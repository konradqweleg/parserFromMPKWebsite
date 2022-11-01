package parserVehicleStop;

import org.example.lineNumbersParser.LineData;


public class LinkLineTimeTable {
    private String link;
    private LineData lineData;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LineData getLineData() {
        return lineData;
    }

    public void setLineData(LineData lineData) {
        this.lineData = lineData;
    }

    public LinkLineTimeTable(String link, LineData lineStatus) {
        this.link = link;
        this.lineData = lineStatus;
    }

}
