package parserVehicleStop;

public class VehicleStopNumber {
    public VehicleStop getVehicleStop() {
        return vehicleStop;
    }

    public void setVehicleStop(VehicleStop vehicleStop) {
        this.vehicleStop = vehicleStop;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    private VehicleStop vehicleStop;
    private int sequenceNumber;

    public  VehicleStopNumber(VehicleStop vehStop, int seqNum){
         vehicleStop=vehStop;
         sequenceNumber=seqNum;
    }

    public  VehicleStopNumber(){

    }

    @Override
    public String toString() {
        return "VehicleStopNumber{" +
                "vehicleStop=" + vehicleStop +
                ", sequenceNumber=" + sequenceNumber +
                '}';
    }



}
