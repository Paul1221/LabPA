package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Triangle implements MyShape {
    private int numberOfSides;
    private int size;
    private double cordX;
    private double cordY;
    private String color;

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


    public String getColor() {
        return color;
    }

    public Triangle(double cordX, double cordY, int size) {
        this.numberOfSides = 3;
        this.size = size;
        this.cordX = cordX;
        this.cordY = cordY;
    }
    public void drawShape(GraphicsContext gc , Canvas canvas) {
        gc.fillPolygon(new double[]{cordX - size, cordX, cordX + size}, new double[]{cordY + size, cordY, cordY + size}, 3);
    }
    public void eraseShape(GraphicsContext gc , Canvas canvas) {
        size = size +1 ;
        gc.fillPolygon(new double[]{cordX - size, cordX, cordX + size}, new double[]{cordY + size, cordY, cordY + size}, 3);
    }
}
