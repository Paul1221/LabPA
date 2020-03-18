import javax.print.Doc;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class Catalog implements Serializable {
    private ArrayList<Document> documents;

    public Catalog(Document[] documente) {
        documents=new ArrayList<>();
        documents.addAll(asList(documente));
    }


    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void addDocument(Document doc){
        documents.add(doc);
    }



    @Override
    public String toString() {
        return "Catalog{" +
                "documents=" + documents +
                '}';
    }
}
