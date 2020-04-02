package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;



public interface MyShape {


    public int getNumberOfSides();
    public int getSize();
    public double getCordX();
    public double getCordY();
    public String getFillColor();
    public String getStrokeColor();

    public void drawShape(GraphicsContext gc , Canvas canvas);
    public void eraseShape(GraphicsContext gc , Canvas canvas);
    public boolean isInCoords(double mouseX,double mouseY);


}
