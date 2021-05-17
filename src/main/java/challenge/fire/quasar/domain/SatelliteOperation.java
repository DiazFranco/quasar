package challenge.fire.quasar.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SatelliteOperation {

    public SatelliteOperation() {
    }

    private List<Satellite> satellites;

    public SatelliteOperation(List<Satellite> satellites) {
        this.satellites = satellites;
    }

    public List<Satellite> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<Satellite> satellites) {
        this.satellites = satellites;
    }

    public double[] getDistances(){
        double [] distances = new double[satellites.size()];
        for (int i = 0; i < satellites.size(); i++) {
            distances[i] = getSatellites().get(i).getDistance();
        }
        return distances;
    }

    public double[][] getPositions(){
        double[][] positions = new double[satellites.size()][];
        String[] coordinates;
        for (int i = 0; i < satellites.size(); i++) {
            if(satellites.get(i).getPosition() != null) {
                coordinates = String.valueOf(satellites.get(i).getPosition()).split(",");
                positions[i] = Arrays.stream(coordinates)
                        .map(Double::valueOf)
                        .mapToDouble(Double::doubleValue)
                        .toArray();
            }
        }
        return positions;
    }

    public void setPositions(double[][] coordinates){
        Position position;
        for (int i = 0; i < coordinates.length; i++) {
            position = new Position(coordinates[i]);
            satellites.get(i).setPosition(position);
        }
    }

    public List<List<String>> getMessages() {
        List<List<String>> messages = new ArrayList<List<String>>();
        for (Satellite sat: satellites) {
            messages.add(sat.getMessage());
        }
        return messages;
    }
}
