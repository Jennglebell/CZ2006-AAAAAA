package Model;

/**
 * Location class
 */
public class Location {

    private String longti;
    private String lati;

    public Location(String longti, String lati) {
        this.longti = longti;
        this.lati = lati;
    }

    public String getLonti() {
        return longti;
    }

    public String getLati() {
        return lati;
    }

}
