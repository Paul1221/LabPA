package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Circle implements MyShape {
    private int numberOfSides;
    private int size;
    private double cordX;
    private double cordY;
    private String fillColor;
    private String strokeColor;

    public int getNumberOfSides() {
        return numberOfSides;
    }

    public int getSize() {
        return size;
    }


    public double getCordX() {
        return cordX;
    }


    public double getCordY() {
        return cordY;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getStrokeColor() {
        return strokeColor;
    }

    public Circle(  double cordX, double cordY,int size, String fillColor, String strokeColor) {
        this.numberOfSides = 1;
        this.size = size;
        this.cordX = cordX;
        this.cordY = cordY;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
    }
    public void drawShape(GraphicsContext gc , Canvas canvas) {
        gc.fillOval(cordX- size / 2, cordY- size / 2, size, size);
        gc.strokeOval(cordX- size / 2, cordY- size / 2, size, size);
    }
    public void eraseShape(GraphicsContext gc , Canvas canvas) {
        gc.fillOval(cordX - size / 2, cordY- size / 2 , size, size);
        gc.strokeOval(cordX- size / 2 , cordY- size / 2 , size, size);
    }
    public boolean isInCoords(double mouseX, double mouseY){
        double distanceToCenter = Math.sqrt((mouseX-cordX)*(mouseX-cordX) + (mouseY-cordY)*(mouseY-cordY));
        if(distanceToCenter <= size/2) {
            System.out.println("Cerc");
            return true;
        }
        return false;
    }
}
