package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Triangle implements MyShape {
    private int numberOfSides;
    private int size;
    private double xA,xB,xC,yA,yB,yC;
    private String fillColor;
    private String strokeColor;
    private double cordX,cordY;

    public int getNumberOfSides() {
        return numberOfSides;
    }

    public int getSize() {
        return size;
    }

    @Override
    public double getCordX() {
        return cordX;
    }
    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getStrokeColor() {
        return strokeColor;
    }
    @Override
    public double getCordY() {
        return cordY;
    }

    public double getxA() {
        return xA;
    }

    public double getxB() {
        return xB;
    }

    public double getxC() {
        return xC;
    }

    public double getyA() {
        return yA;
    }

    public double getyB() {
        return yB;
    }

    public double getyC() {
        return yC;
    }



    public Triangle(  double cordX, double cordY,int size, String fillColor, String strokeColor)  {
        this.numberOfSides = 3;
        this.size = size;
        this.fillColor=fillColor;
        this.strokeColor=strokeColor;
        xA=cordX;
        yA=cordY-size;
        xB=cordX -Math.sqrt(3)/2*size;
        yB=cordY + size/3;
        xC=cordX + Math.sqrt(3)/2*size;
        yC=cordY + size/3;
    }
    public void drawShape(GraphicsContext gc , Canvas canvas) {
        gc.fillPolygon(new double[]{xB, xA, xC}, new double[]{yB, yA, yC}, 3);
        gc.strokePolygon(new double[]{xB, xA, xC}, new double[]{yB, yA, yC}, 3);

    }
    public void eraseShape(GraphicsContext gc , Canvas canvas) {
        gc.fillPolygon(new double[]{xB, xA, xC}, new double[]{yB, yA, yC}, 3);
        gc.strokePolygon(new double[]{xB, xA, xC}, new double[]{yB, yA, yC}, 3);
    }
    public boolean isInCoords(double mouseX, double mouseY){
        double distanceAtoP = Math.sqrt((mouseX-xA)*(mouseX-xA) + (mouseY-yA)*(mouseY-yA));
        double distanceBtoP = Math.sqrt((mouseX-xB)*(mouseX-xB) + (mouseY-yB)*(mouseY-yB));
        double distanceCtoP = Math.sqrt((mouseX-xC)*(mouseX-xC) + (mouseY-yC)*(mouseY-yC));
        if(distanceAtoP <= size*3/2 && distanceBtoP <= size*3/2 && distanceCtoP <= size*3/2) {
            return true;
        }
        return false;
    }
}
