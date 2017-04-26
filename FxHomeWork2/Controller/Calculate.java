package FxHomeWork2.Controller;

import java.util.ArrayList;
import java.util.List;
public class Calculate {
    //ускорение свободного падения
    private static final double FREE_FALL_ACCELERATION = 9.81;

    private static double frequency;
    private double time = 0;
    private List<Double> xValues = new ArrayList<>();
    private List<Double> yValues = new ArrayList<>();
    private List<List<Double>> values = new ArrayList<>();

    //начальные условия
    private InitialParameters INITIAL_PARAMETERS;

    //конструктор с заданными начальными условиями
    public Calculate(InitialParameters INITIAL_PARAMETERS) {
        this.INITIAL_PARAMETERS = INITIAL_PARAMETERS;
        frequency = 1 / INITIAL_PARAMETERS.getFrequency();
    }

    public List<List<Double>> getCurrentCoordinates() {
        double coordinateX;
        double coordinateY = INITIAL_PARAMETERS.getCooridnateY();
        while (coordinateY >= 0) {
            coordinateX = INITIAL_PARAMETERS.getCoordinateX() + INITIAL_PARAMETERS.getVelocityX() * time;
            coordinateY = INITIAL_PARAMETERS.getCooridnateY() + INITIAL_PARAMETERS.getVelocityY() * time - FREE_FALL_ACCELERATION * Math.pow(time,2)/2;
            time += frequency;
            if(coordinateY < 0){
                //сглаживание последней точки
                double xPrev = xValues.get(xValues.size()-1);
                double yPrev = yValues.get(yValues.size()-1);
                yValues.add(0.0);
                xValues.add(xPrev +  yPrev /(yPrev - coordinateY)  * (coordinateX-xPrev));
            }
            else {
                yValues.add(coordinateY);
                xValues.add(coordinateX);
            }
        }
        values.add(xValues);
        values.add(yValues);
        return values;
    }
    public double fallingTime() {
        return time;
    }
    public double calculatePathLength() {
        double pathLength = 0;
        for(int i = 1; i < values.get(0).size(); i++){
            double x1 = values.get(0).get(i-1);
            double x2 = values.get(0).get(i);
            double y1 = values.get(1).get(i-1);
            double y2 = values.get(1).get(i);
            pathLength+=Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1, 2));
        }
        return pathLength;
    }
    public double calculateMaxHeight() {
        double maxHeight = values.get(1).get(0);
        for(int i = 1; i < values.get(0).size(); i++){
            maxHeight = Math.max(maxHeight, values.get(1).get(i));
        }
        return maxHeight;
    }
}
