import java.io.Serializable;

public class Document implements Serializable {
    private int ID;
    private String name;
    private DocumentType tipDocument;
    private String path;

    public Document(int ID, String name, DocumentType tipDocument, String path) {
        this.ID = ID;
        this.name = name;
        this.tipDocument = tipDocument;
        this.path = path;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DocumentType getTipDocument() {
        return tipDocument;
    }

    public void setTipDocument(DocumentType tipDocument) {
        this.tipDocument = tipDocument;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Document{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", tipDocument=" + tipDocument +
                ", path='" + path + '\'' +
                '}';
    }
}
