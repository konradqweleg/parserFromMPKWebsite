package parserVehicleStop;

import org.example.lineNumbersParser.LineStatus;

public class LineVehicleStops {
    private LineStatus line;
    private VehicleStopForLine firstDirection;
    private VehicleStopForLine secondDirection;
    
    public LineVehicleStops(LineStatus line,VehicleStopForLine first,VehicleStopForLine second){
        this.line=line;
        firstDirection=first;
        secondDirection=second;

    }

    public LineStatus getLine() {
        return line;
    }

    public VehicleStopForLine getFirstDirection() {
        return firstDirection;
    }

    public VehicleStopForLine getSecondDirection() {
        return secondDirection;
    }
}
