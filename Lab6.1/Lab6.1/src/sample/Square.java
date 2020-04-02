package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Square implements MyShape {
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

    public Square(  double cordX, double cordY,int size, String fillColor, String strokeColor) {
        this.numberOfSides = 4;
        this.size = size;
        this.cordX = cordX;
        this.cordY = cordY;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
    }

    public void drawShape(GraphicsContext gc , Canvas canvas) {
        gc.fillPolygon(new double[]{getCordX() - size / 2, getCordX() + size / 2, getCordX() + size / 2, getCordX() - size / 2}, new double[]{getCordY() - size / 2, getCordY() - size / 2, getCordY() + size / 2, getCordY() + size / 2}, 4);
        gc.strokePolygon(new double[]{getCordX() - size / 2, getCordX() + size / 2, getCordX() + size / 2, getCordX() - size / 2}, new double[]{getCordY() - size / 2, getCordY() - size / 2, getCordY() + size / 2, getCordY() + size / 2}, 4);

    }
    public void eraseShape(GraphicsContext gc , Canvas canvas) {
        gc.fillPolygon(new double[]{getCordX() - size / 2, getCordX() + size / 2, getCordX() + size / 2, getCordX() - size / 2}, new double[]{getCordY() - size / 2, getCordY() - size / 2, getCordY() + size / 2, getCordY() + size / 2}, 4);
        gc.strokePolygon(new double[]{getCordX() - size / 2, getCordX() + size / 2, getCordX() + size / 2, getCordX() - size / 2}, new double[]{getCordY() - size / 2, getCordY() - size / 2, getCordY() + size / 2, getCordY() + size / 2}, 4);

    }
    public boolean isInCoords(double mouseX, double mouseY){
        if(mouseX<=cordX+size/2 && mouseX>= cordX-size/2 && mouseY<=cordY+size/2 && mouseY>= cordY-size/2) {
            return true;
        }
        return false;
    }
}
