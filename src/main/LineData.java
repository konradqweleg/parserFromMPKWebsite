package main;

public class LineData {

    private int numberLine;
    private LineStatus statusLine;

    public int getNumberLine() {
        return numberLine;
    }

    @Override
    public String toString() {
        return "main.LineData{" +
                "numberLine=" + numberLine +
                ", statusLine=" + statusLine +
                '}';
    }

    public void setNumberLine(int numberLine) {
        this.numberLine = numberLine;
    }

    public LineStatus getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(LineStatus statusLine) {
        this.statusLine = statusLine;
    }

    public LineData(int numberLine, LineStatus statusLine) {
        this.numberLine = numberLine;
        this.statusLine = statusLine;
    }
}
