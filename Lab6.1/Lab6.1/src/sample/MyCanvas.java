package sample;


import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MyCanvas extends Canvas {
    List<MyShape> shapeList = new ArrayList<>();
    GraphicsContext gc = this.getGraphicsContext2D();

    public void addShape(  double cordX, double cordY,int sidesNumber,int size, String fillColor, String strokeColor)  {
        System.out.println(sidesNumber);
        if (sidesNumber == 1) {

            shapeList.add(new Circle(cordX, cordY, size,fillColor,strokeColor));
        }
        if (sidesNumber == 3) {
            shapeList.add(new Triangle(cordX, cordY, size,fillColor,strokeColor));
        }
        if (sidesNumber == 4) {
            shapeList.add(new Square(cordX, cordY, size,fillColor,strokeColor));
        }
    }
    

    public List<MyShape> getShapeList() {
        return shapeList;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public Pair<String,String> getSetting(String fillColor, String strokeColor) {
        String fill = null,stroke=null;
        if (!strokeColor.equals("Random")) {
            gc.setStroke(Color.web(strokeColor.toLowerCase(), 1.0D));
        } else {
            List<String> givenList = Arrays.asList("Red", "Green", "Yellow");
            Random rand = new Random();
             stroke = givenList.get(rand.nextInt(givenList.size()));
            gc.setStroke(Color.web(stroke, 1.0D));
        }
        if (!fillColor.equals("Random")) {
            gc.setFill(Color.web(fillColor.toLowerCase(), 1.0D));
        } else {
            List<String> givenList = Arrays.asList("Red", "Green", "Yellow");
            Random rand = new Random();
             fill = givenList.get(rand.nextInt(givenList.size()));
            gc.setFill(Color.web(fill, 1.0D));
        }
        return new Pair<String,String>(fill,stroke);
    }

    public void deleteShape(double mouseX,double mouseY){
        int flag=0;
        for(int i=shapeList.size()-1;i>=0 && flag==0;i--){
            if(shapeList.get(i).isInCoords(mouseX,mouseY)){
                flag=1;
                shapeList.remove(i);
            }
        }

        if(flag==1){
            this.getSetting("White", "White");
            getGc().fillRect(0, 0, getWidth(), getHeight());
            for(MyShape s : shapeList){
                this.getSetting(s.getFillColor(), s.getStrokeColor());
                s.drawShape(gc,this);
            }
        }
    }

    public void reset() {

        this.getSetting("White", "White");
        shapeList.clear();
        gc.setFill(Color.web("white", 1.0D));
        gc.setStroke(Color.web("white", 1.0D));
        getGc().fillRect(0, 0, getWidth(), getHeight());
    }

    public void addImage(String path){
        System.out.println(path);
        Image img = new Image(path);
        gc.drawImage(img,0,0);
    }

    public void saveImage(File file) throws IOException {
        try{
            WritableImage writableImage = new WritableImage((int)this.getWidth(), (int)this.getHeight());
            this.snapshot(null, writableImage);
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
            ImageIO.write(renderedImage, "png", file);
        } catch (IOException ex) {

        }

    }


}
