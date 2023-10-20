public class Restaurant extends Locations{
    //Construtor:
    public Restaurant(int id, String name, String description, String image, String link) {
        super("Restaurante", id, name, description,image, link);
    }

    //To String:
    public String toString(){
        return super.toString();
    }
}
