package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Square implements MyShape {
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

    public Square(double cordX, double cordY, int size) {
        this.numberOfSides = 4;
        this.size = size;
        this.cordX = cordX;
        this.cordY = cordY;
    }
    public void drawShape(GraphicsContext gc , Canvas canvas) {
        gc.fillPolygon(new double[]{getCordX() - size / 2, getCordX() + size / 2, getCordX() + size / 2, getCordX() - size / 2}, new double[]{getCordY() - size / 2, getCordY() - size / 2, getCordY() + size / 2, getCordY() + size / 2}, 4);
    }
    public void eraseShape(GraphicsContext gc , Canvas canvas) {
        size +=1;
        gc.fillPolygon(new double[]{getCordX() - size / 2, getCordX() + size / 2, getCordX() + size / 2, getCordX() - size / 2}, new double[]{getCordY() - size / 2, getCordY() - size / 2, getCordY() + size / 2, getCordY() + size / 2}, 4);
    }
}
