public class Hospital extends Locations{
    public Hospital(int id, String name, String description, String image, String link) {
        super("Hospital", id, name, description, image, link);
    }

    //To String:
    public String toString(){
        return super.toString();
    }
}
