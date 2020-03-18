import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] argv) throws IOException {
        Document d1=new Document(1,"cursGrafuri",DocumentType.PDF,"D:\\Facultate\\Grafuri-mergedcurs11.pdf");
        Catalog c1=new Catalog(new Document[]{d1});
        Operatii.save(c1,"da.ser");
        Operatii.load("da.ser");
        Operatii.view(d1.getPath());
    }
}
