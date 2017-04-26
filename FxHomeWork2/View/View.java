package FxHomeWork2.View;

import FxHomeWork2.Model.Model;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class View extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        List<TextField> listOfConstantFields = new ArrayList<>();
        BorderPane borderPane = new BorderPane();
        //borderPane.setPadding(new Insets(10));

        FlowPane flowPane = addFlowPane(borderPane, listOfConstantFields);
        borderPane.setBottom(flowPane);

        VBox constBox = addRightBox(listOfConstantFields);
        borderPane.setRight(constBox);

        Scene scene = new Scene(borderPane, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private Button addInfoButton(){
        Image imageInfo = new Image(getClass().getResourceAsStream("info-icon.png"));
        Button infoButton = new Button("", new ImageView(imageInfo));
        infoButton.setOnAction(event -> {
            Model.actionInfoButton();
        });
        return infoButton;

    }

    private VBox addRightBox(List<TextField> listOfConstantFields){
        VBox rightBox = new VBox();
        rightBox.setSpacing(10);
        rightBox.setStyle("-fx-border-color: #D2B48C; -fx-border-width: 0 0 0 2 ;");
        rightBox.setPadding(new Insets(10));
        AnchorPane anchorBtn = new AnchorPane();
        Button infoButton = addInfoButton();
        anchorBtn.getChildren().add(infoButton);
        AnchorPane.setRightAnchor(infoButton, 0.0);
        rightBox.getChildren().add(anchorBtn);

        Label lengthLabel = new Label("Length of the path(meters):");
        TextField lengthField = new TextField();
        listOfConstantFields.add(lengthField);
        lengthField.setEditable(false);

        Label timeLabel = new Label("Time(seconds):");
        TextField timeField = new TextField();
        timeField.setEditable(false);
        listOfConstantFields.add(timeField);

        Label maxHeight = new Label("Max height(meters):");
        TextField maxHeightField = new TextField();
        maxHeightField.setEditable(false);
        listOfConstantFields.add(maxHeightField);
        rightBox.getChildren().addAll(lengthLabel, lengthField, timeLabel, timeField, maxHeight, maxHeightField);

        return rightBox;
    }
    public static LineChart<Number, Number> addChart(List<List<Double>> values){
        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();
        LineChart<Number, Number> numberLineChart = new LineChart<>(x,y);
        numberLineChart.setCreateSymbols(false);
        numberLineChart.setLegendVisible(false);
        numberLineChart.setTitle("Angular Motion: trajectory");
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        ObservableList<XYChart.Data<Number, Number>> datas = FXCollections.observableArrayList();
        for(int i = 0; i < values.get(0).size();i ++){
            datas.add(new XYChart.Data<>(values.get(0).get(i), values.get(1).get(i)));

        }
        series.setData(datas);
        numberLineChart.getData().add(series);
        return numberLineChart;
    }
    private  FlowPane addFlowPane(BorderPane borderPane, List<TextField> listOfConstantFields){
        FlowPane flowPane = new FlowPane(10,10);

        flowPane.setStyle("-fx-border-color: #D2B48C;-fx-border-width: 2 0 0 0;");
        flowPane.setPadding(new Insets(10));
        List<TextField> listOfFields = new ArrayList<>();

        VBox velBox = addVBox("VelocityX: ", "VelocityY: ", listOfFields);
        VBox posBox = addVBox("PositionX: ", "PositionY: ", listOfFields);

        VBox lastVBox = new VBox();
        lastVBox.setSpacing(10);
        HBox hBox = addHBox("Frequency:", listOfFields);
        Button calculateBut = new Button("Calculate");
        calculateBut.setOnAction(event -> Model.actionCalculateButton(borderPane, listOfFields, listOfConstantFields));
        lastVBox.getChildren().addAll(hBox, calculateBut);

        flowPane.getChildren().addAll(velBox,posBox, lastVBox);
        return flowPane;
    }


    private VBox addVBox(String textLabel1, String textLabel2, List<TextField> listOfFields){
        VBox vBox = new VBox();
        vBox.setSpacing(10);

        HBox hBox1 = addHBox(textLabel1, listOfFields);
        HBox hBox2 = addHBox(textLabel2, listOfFields);
        vBox.getChildren().addAll(hBox1, hBox2);
        return vBox;
    }
    private HBox addHBox(String textLabel, List<TextField> listOfFields){
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        Label label = new Label(textLabel);
        TextField textField = new TextField();
        listOfFields.add(textField);
        hBox.getChildren().addAll(label,textField);
        return hBox;
    }
}
