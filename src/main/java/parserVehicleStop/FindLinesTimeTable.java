package parserVehicleStop;

import org.example.lineNumbersParser.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindLinesTimeTable {


  private static final String VEHICLE_STOP_FOR_LINE_CSS="table div div tbody tr td";



  private Optional<VehicleStopNumber> getVehicleStopPointFromElementIfExists(Element element,int elemNumber){
      int NO_ELEM_SIZE=0;

      boolean isHaveContains=(element.text().length()>NO_ELEM_SIZE);
      if(isHaveContains){

        boolean isContainNameVehicleStop=(element.text().split(Utility.REGEX_NUMBER).length>0);

        if(isContainNameVehicleStop){
            String nameVehicleStop=element.text().split("(?<=\\\\D)(?=\\\\d)")[0];
            Integer numberBusStop=null;
            boolean isHaveSpaceBeforePotentialNumber= element.text().split(Utility.REGEX_SPACE).length>0;

            if(isHaveSpaceBeforePotentialNumber){
                String potentialVehicleNumber=element.text().split(Utility.REGEX_SPACE)[element.text().split(Utility.REGEX_SPACE).length-1];
                if(Utility.isNumeric(potentialVehicleNumber)){
                     numberBusStop=Integer.parseInt(potentialVehicleNumber);
                }
            }
            return Optional.of(new VehicleStopNumber(new VehicleStop(nameVehicleStop,numberBusStop,""),elemNumber));


        }


      }

      return Optional.empty();
  }
  public List<VehicleStopNumber> getVehicleStopsForLineFirstDirection(LineData lineData, Document pageLine){
      List<VehicleStopNumber> vehicleStopsList=new ArrayList<>();
      Elements vehicleStops=pageLine.select(VEHICLE_STOP_FOR_LINE_CSS);
      int numberVehicleStopOnList=1;

      for(Element vehicleStopPoint :vehicleStops){
          if(vehicleStopPoint.text().length()>0) {
              vehicleStopsList.add(new VehicleStopNumber(new VehicleStop(vehicleStopPoint.text().split("(?<=\\\\D)(?=\\\\d)")[0],Integer.parseInt(vehicleStopPoint.text().split(" ")[vehicleStopPoint.text().split(" ").length-1]),""),numberVehicleStopOnList));
              numberVehicleStopOnList+=1;
          }
      }

      for(VehicleStopNumber a:vehicleStops){
          System.out.println(a);
      }

      return vehicleStops;
  }


  public static void main(String[]args) throws IOException {
      FindLinksLine xa=new FindLinksLine();
      Optional<LinkLineTimeTable> tb=xa.findLinkToTimetableForNumberLine(new LineData(137,LineStatus.ORDINARY), DiskWebPageAccess.getMainPageDocument());
      System.out.println(tb.get().getLink());

      FindLinesTimeTable x=new FindLinesTimeTable();
      x.getVehicleStopsForLineFirstDirection(new LineData(137, LineStatus.ORDINARY), DiskWebPageAccess.GetPageFromPath(tb.get().getLink()));
  }




}
