package masterpack;

public class Rectangle extends Figure {
    private String name;
    private int lat1;
    private int lat2;



    public Rectangle(String name, int lat1, int lat2) {
        super();
        this.name = name;
        this.lat1 = lat1;
        this.lat2 = lat2;
    }

    public int getLat1() {
        return lat1;
    }

    public void setLat1(int lat1) {
        this.lat1 = lat1;
    }

    public int getLat2() {
        return lat2;
    }

    public void setLat2(int lat2) {
        this.lat2 = lat2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
