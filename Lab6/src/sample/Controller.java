package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Spinner sides;
    @FXML
    private ChoiceBox fillColor;
    @FXML
    private ChoiceBox strokeColor;
    @FXML
    private Canvas canvas;
    @FXML
    private TextField size;
    @FXML
    private GraphicsContext gc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> sidesValues = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4);
        sides.setValueFactory(sidesValues);
        fillColor.getItems().add("Random");
        fillColor.getItems().add("Red");
        fillColor.getItems().add("Green");
        fillColor.getItems().add("Yellow");
        fillColor.setValue("Random");
        strokeColor.getItems().add("Random");
        strokeColor.getItems().add("Red");
        strokeColor.getItems().add("Green");
        strokeColor.getItems().add("Yellow");
        strokeColor.setValue("Random");

        gc = canvas.getGraphicsContext2D();
    }

    public void drawMethod(){

        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            double mouseX,mouseY;
            @Override
            public void handle(MouseEvent event) {
                mouseX=event.getSceneX();
                mouseY=event.getSceneY();
                drawShape(mouseX-100,mouseY-140);

            }
        });
    }

    public void drawShape(double mouseX,double mouseY)  {

        System.out.printf(String.valueOf(mouseX) + mouseY);

        int sidesNumber = Integer.parseInt(sides.getValue().toString());
        int sizeNumber;
        if (!size.getText().equals("")) {
            sizeNumber = Integer.parseInt(size.getText());
        } else {
            sizeNumber = new Random().nextInt(1000);
        }
        if (sidesNumber == 1) {
            getSetting();
            gc.fillOval(mouseX - sizeNumber/2 , mouseY - sizeNumber/2 , sizeNumber, sizeNumber);
        } else if (sidesNumber == 3) {
            getSetting();
            gc.fillPolygon(new double[]{mouseX-sizeNumber,mouseX,mouseX+sizeNumber}, new double[]{mouseY+sizeNumber,mouseY,mouseY+sizeNumber}, 3);
        }else if (sidesNumber == 4) {
            getSetting();
            gc.fillPolygon(new double[]{mouseX-sizeNumber/2,mouseX+sizeNumber/2,mouseX+sizeNumber/2,mouseX-sizeNumber/2}, new double[]{mouseY-sizeNumber/2,mouseY-sizeNumber/2,mouseY+sizeNumber/2,mouseY+sizeNumber/2}, 4);
        }
        System.out.println(sides.getValue().toString() + ' ' + fillColor.getValue().toString() + size.getText());

    }

    private void getSetting() {
        if (!strokeColor.getValue().toString().equals("Random")) {
            gc.setStroke(Color.web(strokeColor.getValue().toString().toLowerCase(), 1.0D));
        } else {
            List<String> givenList = Arrays.asList("Red", "Green", "Yellow");
            Random rand = new Random();
            String randomElement = givenList.get(rand.nextInt(givenList.size()));
            gc.setStroke(Color.web(randomElement, 1.0D));
        }
        if (!fillColor.getValue().toString().equals("Random")) {
            gc.setFill(Color.web(fillColor.getValue().toString().toLowerCase(), 1.0D));
        } else {
            List<String> givenList = Arrays.asList("Red", "Green", "Yellow");
            Random rand = new Random();
            String randomElement = givenList.get(rand.nextInt(givenList.size()));
            gc.setFill(Color.web(randomElement, 1.0D));
        }
    }


}

