package FxHomeWork2.Model;

import FxHomeWork2.Controller.Calculate;
import FxHomeWork2.Controller.InitialParameters;
import FxHomeWork2.View.View;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.util.List;
public class Model {

    private static void clearTextFields(List<TextField> listOfConstantFields, List<TextField> listOfFields){
        listOfFields.get(0).clear();
        listOfFields.get(1).clear();
        listOfFields.get(2).clear();
        listOfFields.get(3).clear();
        listOfConstantFields.get(0).clear();
        listOfConstantFields.get(1).clear();
        listOfConstantFields.get(2).clear();
    }
    public static void actionInfoButton(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("This program calculates the trajectory of the object's motion according to speed and coordinates.");
        alert.showAndWait();
    }
    public static void actionCalculateButton(BorderPane borderPane, List<TextField> listOfFields, List<TextField> listOfConstantFields){
        try{
            InitialParameters initialParameters = new InitialParameters(listOfFields.get(2).getText(),listOfFields.get(3).getText(),
                    listOfFields.get(0).getText(),listOfFields.get(1).getText(),listOfFields.get(4).getText());
            Calculate calcTrajectory  = new Calculate(initialParameters);
            List<List<Double>> values = calcTrajectory.getCurrentCoordinates();
            LineChart<Number, Number> numberLineChart = View.addChart(values);
            borderPane.setCenter(numberLineChart);
            listOfConstantFields.get(0).setText(String.valueOf(calcTrajectory.calculatePathLength()));
            listOfConstantFields.get(1).setText(String.valueOf(calcTrajectory.fallingTime()));
            listOfConstantFields.get(2).setText(String.valueOf(calcTrajectory.calculateMaxHeight()));

        }catch (IllegalArgumentException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
            borderPane.setCenter(null);
            Model.clearTextFields(listOfConstantFields, listOfFields);
        }catch (OutOfMemoryError e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: out of memory. Arguments are too big");
            alert.showAndWait();
            borderPane.setCenter(null);
            Model.clearTextFields(listOfConstantFields, listOfFields);

        }
    }
}
