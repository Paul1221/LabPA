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
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.util.Pair;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ChoiceBox<String> shape;
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

        shape.getItems().add("Random");
        shape.getItems().add("Circle");
        shape.getItems().add("Triangle");
        shape.getItems().add("Square");
        shape.setValue("Random");
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

    private int getSidesNumber(){
        if(shape.getValue().equals("Random")){
            List<Integer> givenList = Arrays.asList(1, 3, 4);
            return givenList.get(new Random().nextInt(givenList.size()));
        }
        else if(shape.getValue().equals("Circle")){
            return 1;
        }
        else if(shape.getValue().equals("Triangle")){
            return 3;
        }
        else {
            return 4;
        }

    }

    public void drawMethod() {

        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            double mouseX, mouseY;

            @Override
            public void handle(MouseEvent event) {
                if (mode.getValue().toString().equals("Draw")) {
                    mouseX = event.getSceneX();
                    mouseY = event.getSceneY();
                    int sizeNumber;
                    int sidesNumber=getSidesNumber();

                    if (!size.getText().equals("")) {
                        sizeNumber = Integer.parseInt(size.getText());
                    } else {
                        sizeNumber = new Random().nextInt(1000);
                    }
                    Pair<String, String> colors = canvas.getSetting(fillColor.getValue().toString(), strokeColor.getValue().toString());
                    canvas.addShape(mouseX - 98, mouseY - 140, sidesNumber, sizeNumber, colors.getKey(), colors.getValue());
                    canvas.getShapeList().get(canvas.getShapeList().size() - 1).drawShape(canvas.getGc(), canvas);

                } else {
                    mouseX = event.getSceneX();
                    mouseY = event.getSceneY();
                    canvas.deleteShape(mouseX - 98, mouseY - 140);

                }
            }

        });
    }

    public void openImage(){
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add( new FileChooser.ExtensionFilter("Images","*.png"));
        File selectedFile=chooser.showOpenDialog(null);
        if(selectedFile != null) {
            String selectedImagePath = selectedFile.getAbsolutePath();
           canvas.addImage("file:"+selectedImagePath);
        }
    }

    public void saveImage() throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add( new FileChooser.ExtensionFilter("Images","*.png"));
        File selectedFile=chooser.showOpenDialog(null);
        if (selectedFile != null) {
            String directoryPath = selectedFile.getAbsolutePath();
            canvas.saveImage(selectedFile);
        }
    }

    public void resetCanvas() {
        canvas.reset();
    }


}

