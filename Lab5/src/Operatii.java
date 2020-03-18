import java.awt.*;
import java.io.*;

public class Operatii {

    public static void save(Catalog catalog, String  filename){
        try
        {

            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(catalog);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        }

        catch(IOException ex)
        {
            new Exception("IOException is caught");
        }
    }

    public static void load(String filename){
        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            Catalog catalog = (Catalog) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
            System.out.println(catalog.toString());
        }

        catch(IOException ex)
        {
            new Exception("IOException is caught");
        }

        catch(ClassNotFoundException ex)
        {
            new Exception("ClassNotFoundException is caught");
        }
    }
    public static void view (String path) throws IOException {
        Desktop.getDesktop().open(new File(path));
    }
}
