package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;



public interface MyShape {


    public int getNumberOfSides();
    public int getSize();
    public double getCordX();
    public double getCordY();


    public void drawShape(GraphicsContext gc , Canvas canvas);
    public void eraseShape(GraphicsContext gc , Canvas canvas);



}
