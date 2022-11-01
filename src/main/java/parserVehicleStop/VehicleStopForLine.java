package parserVehicleStop;

import java.util.ArrayList;
import java.util.List;

public class VehicleStopForLine {
    public List<VehicleStopNumber> getVehicleStops() {
        return vehicleStops;
    }

    private List<VehicleStopNumber> vehicleStops;



    public void addVehicleStop(VehicleStop vehicleStop){
        int nextElemSequenceNumber=(vehicleStops.size())+1;
        vehicleStops.add(new VehicleStopNumber(vehicleStop,nextElemSequenceNumber));

    }
    VehicleStopForLine(){
        vehicleStops=new ArrayList<>();
    }
}
