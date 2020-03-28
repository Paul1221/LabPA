package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Pair;


import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Spinner<Integer> sides;
    @FXML
    private ChoiceBox<String> fillColor;
    @FXML
    private ChoiceBox<String> strokeColor;
    @FXML
    private MyCanvas canvas;
    @FXML
    private TextField size;
    @FXML
    private ChoiceBox<String> mode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        canvas.gc.setFill(Color.web("white", 1.0D));
        canvas.gc.setStroke(Color.web("white", 1.0D));
        canvas.getGc().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
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
        mode.getItems().add("Draw");
        mode.getItems().add("Erase");
        mode.setValue("Draw");

    }


    public void drawMethod() {

        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            double mouseX, mouseY;

            @Override
            public void handle(MouseEvent event) {
                if(mode.getValue().toString().equals("Draw")) {
                    mouseX = event.getSceneX();
                    mouseY = event.getSceneY();
                    int sizeNumber;
                    int sidesNumber = Integer.parseInt(sides.getValue().toString());
                    if(sidesNumber!=2) {
                        if (!size.getText().equals("")) {
                            sizeNumber = Integer.parseInt(size.getText());
                        } else {
                            sizeNumber = new Random().nextInt(1000);
                        }
                        Pair<String,String> colors =canvas.getSetting(fillColor.getValue().toString(), strokeColor.getValue().toString());
                        canvas.addShape(mouseX - 98, mouseY - 140, sidesNumber, sizeNumber, colors.getKey(), colors.getValue());
                        canvas.getShapeList().get(canvas.getShapeList().size() - 1).drawShape(canvas.getGc(), canvas);
                    }
                }
                else{
                    mouseX = event.getSceneX();
                    mouseY = event.getSceneY();
                    canvas.deleteShape(mouseX-98,mouseY-140);

                }
            }

        });
    }


    public void resetCanvas(){
        canvas.reset();
    }


}

