public abstract class News {
    private int id;
    private String headline;
    private String image;
    private String link;

    //gets:
    public int getId() {
        return this.id;
    }
    public String getHeadline() {
        return this.headline;
    }
    public String getImage() {
        return this.image;
    }
    public String getLink() {
        return this.link;
    }

//    //sets:
//    public void setHeadline(String headline) {
//        this.headline = headline;
//    }
//    public void setImage(String image) {
//        this.image = image;
//    }
//    public void setLink(String link) {
//        this.link = link;
//    }

    //Construtor:
    public News(int id, String headline, String image, String link){
        this.id = id;
        this.headline = headline;
        this.image = image;
        this.link = link;
    }

    //To String:
    public String toString() {
        return "\nID: " + this.id + ", Descrição: " + this.headline +
                ", Imagem: " + this.image + ", Link: " + this.link;
    }
}
