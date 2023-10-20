public abstract class Locations {
    private int id;
    private String name;
    private String description;
    private String image;
    private String link;
    private String local;

    //gets:
    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public String getImage() {
        return this.image;
    }
    public String getLink() {
        return this.link;
    }
    public String getLocal() {
        return this.local;
    }

    //sets:
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setLink(String link) {
        this.link = link;
    }

    //Construtor:
    public Locations(String local, int id, String name, String description, String image, String link){
        this.local = local;
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.link = link;
    }

    //To String:
    public String toString() {
        return "\nLocal: " + this.local + ", ID: " + this.id + ", Nome: " + this.name + ", Descrição: " + this.description +
                ", Imagem: " + this.image + ", Link: " + this.link;
    }
}
