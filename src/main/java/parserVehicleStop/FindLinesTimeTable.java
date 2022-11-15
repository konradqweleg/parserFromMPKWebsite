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

  private String removeNoNeedNumberAndSpaceStartFromNameVehicleStop(String nameVehicleStopRaw){
      return nameVehicleStopRaw.replaceAll(Utility.REGEX_NUMBER2,Utility.REGEX_NO_CHAR).trim();
  }

  private Optional<Integer> getPotentialNumberVehicleStop(String nameVehicleStopRaw){
      boolean isNameVehicleStopContainsNumberVehicleStop= nameVehicleStopRaw.matches(Utility.REGEX_IS_TEXT_CONTAINS_NUMBER);

      if(isNameVehicleStopContainsNumberVehicleStop) {
          String potentialVehicleStopNumber=nameVehicleStopRaw.replaceAll(Utility.REGEX_VISIBLE_CHAR, Utility.REGEX_NO_CHAR);
          if (Utility.isNumeric(potentialVehicleStopNumber)) {
              return  Optional.of(Integer.parseInt(potentialVehicleStopNumber));
          }
      }

      return Optional.empty();
  }




  private boolean isTextContainsVehicleStopName(String potentialNameVehicle){
      int NO_ELEM_SIZE=0;
      boolean isHaveContains=(potentialNameVehicle.length()>NO_ELEM_SIZE);
      boolean isContainNameVehicleStop=(potentialNameVehicle.split(Utility.REGEX_NUMBER).length>NO_ELEM_SIZE);

      return isHaveContains && isContainNameVehicleStop;
  }


  private Optional<VehicleStopNumber> getVehicleStopPointFromElementIfExists(Element element,int elemNumber){

        if(isTextContainsVehicleStopName(element.text())){
            String nameVehicleStopRaw=element.text();
            String nameVehicleStop=removeNoNeedNumberAndSpaceStartFromNameVehicleStop(nameVehicleStopRaw);
            String defaultDescription="";
            Optional<Integer> numberBusStop=getPotentialNumberVehicleStop(nameVehicleStopRaw);
            return Optional.of(new VehicleStopNumber(new VehicleStop(nameVehicleStop,numberBusStop,defaultDescription),elemNumber));
        }

      return Optional.empty();
  }
  public List<VehicleStopNumber> getVehicleStopsForLineFirstDirection(LineData lineData, Document pageLine){
      List<VehicleStopNumber> vehicleStopsList=new ArrayList<>();
      Elements vehicleStops=pageLine.select(VEHICLE_STOP_FOR_LINE_CSS);
      int START_ITERATION_VEHICLE_STOP_NUMBER=1;
      int STEP_ITERATION_VEHICLE_STOP_NUMBER=1;
      int numberVehicleStopOnList=START_ITERATION_VEHICLE_STOP_NUMBER;

      for(Element vehicleStopPointElem :vehicleStops){

          Optional<VehicleStopNumber> vehicleStopNumber= getVehicleStopPointFromElementIfExists(vehicleStopPointElem,numberVehicleStopOnList);
          if(vehicleStopNumber.isPresent()){
              vehicleStopsList.add(vehicleStopNumber.get());
              numberVehicleStopOnList+=STEP_ITERATION_VEHICLE_STOP_NUMBER;
          }

      }

      for(VehicleStopNumber a:vehicleStopsList){
          System.out.println(a);
      }

      return vehicleStopsList;
  }


  public static void main(String[]args) throws IOException {
      FindLinksLine xa=new FindLinksLine();
      Optional<LinkLineTimeTable> tb=xa.findLinkToTimetableForNumberLine(new LineData(137,LineStatus.ORDINARY), DiskWebPageAccess.getMainPageDocument());
      System.out.println(tb.get().getLink());

      FindLinesTimeTable x=new FindLinesTimeTable();
      x.getVehicleStopsForLineFirstDirection(new LineData(137, LineStatus.ORDINARY), DiskWebPageAccess.GetPageFromPath(tb.get().getLink()));
  }




}
