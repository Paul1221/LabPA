package sample;


import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;


import java.net.URL;
import java.util.*;

public class MyCanvas extends Canvas {
    List<MyShape> shapeList = new ArrayList<>();
    GraphicsContext gc = this.getGraphicsContext2D();

    public void addShape(double cordX, double cordY, int sidesNumber, int size) {
        if (sidesNumber == 1) {
            shapeList.add(new Circle(cordX, cordY, size));
        }
        if (sidesNumber == 3) {
            shapeList.add(new Triangle(cordX, cordY, size));
        }
        if (sidesNumber == 4) {
            shapeList.add(new Square(cordX, cordY, size));
        }
    }
    

    public List<MyShape> getShapeList() {
        return shapeList;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void getSetting(String fillColor, String strokeColor) {
        if (!strokeColor.equals("Random")) {
            gc.setStroke(Color.web(strokeColor.toLowerCase(), 1.0D));
        } else {
            List<String> givenList = Arrays.asList("Red", "Green", "Yellow");
            Random rand = new Random();
            String randomElement = givenList.get(rand.nextInt(givenList.size()));
            gc.setStroke(Color.web(randomElement, 1.0D));
        }
        if (!fillColor.equals("Random")) {
            gc.setFill(Color.web(fillColor.toLowerCase(), 1.0D));
        } else {
            List<String> givenList = Arrays.asList("Red", "Green", "Yellow");
            Random rand = new Random();
            String randomElement = givenList.get(rand.nextInt(givenList.size()));
            gc.setFill(Color.web(randomElement, 1.0D));
        }
    }

    public void reset() {

        this.getSetting("White", "White");
        shapeList.clear();
        gc.setFill(Color.web("white", 1.0D));
        gc.setStroke(Color.web("white", 1.0D));
        getGc().fillRect(0, 0, getWidth(), getHeight());
    }


}
