package parserVehicleStop;

import java.util.Optional;

public class VehicleStop {
    private String name;
    private Optional<Integer> number;
    private  String description;

    public VehicleStop(String name, Optional<Integer> number, String description) {
        this.name = name;
        this.number = number;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<Integer> getNumber() {
        return number;
    }

    public void setNumber(Optional number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "VehicleStop{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", description='" + description + '\'' +
                '}';
    }
}
